// MainWindow.axaml.cs
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using SpreadsheetApp.Models;
using SpreadsheetApp.Core;
using System;
using System.Collections.Generic;
using System.IO;
using Avalonia.Controls.Primitives;

namespace SpreadsheetApp
{
    public partial class MainWindow : Window
    {
        // НЕ використовуємо імена, що співпадають із x:Name в XAML
        private StackPanel _gridPanel;
        private TextBox _rowsBox, _colsBox;
        private ToggleButton _showValuesToggle;
        private SpreadsheetModel model;

        public MainWindow()
        {
            InitializeComponent();
#if DEBUG
            // AttachDevTools може бути відсутній у вашій версії Avalonia; якщо є — можна додати using Avalonia.Diagnostics та викликати
            // this.AttachDevTools();
#endif
            _gridPanel = this.FindControl<StackPanel>("GridPanel");
            _rowsBox = this.FindControl<TextBox>("RowsBox");
            _colsBox = this.FindControl<TextBox>("ColsBox");
            _showValuesToggle = this.FindControl<ToggleButton>("ShowValuesToggle");

            var resizeBtn = this.FindControl<Button>("ResizeBtn");
            resizeBtn.Click += ResizeBtn_Click;
            var saveBtn = this.FindControl<Button>("SaveBtn");
            saveBtn.Click += SaveBtn_Click;
            var loadBtn = this.FindControl<Button>("LoadBtn");
            loadBtn.Click += LoadBtn_Click;
            _showValuesToggle.Checked += (s,e)=> RenderGrid();
            _showValuesToggle.Unchecked += (s,e)=> RenderGrid();

            model = new SpreadsheetModel(6, 8);
            _rowsBox.Text = model.Rows.ToString();
            _colsBox.Text = model.Cols.ToString();
            RenderGrid();
        }

        private void InitializeComponent()
        {
            AvaloniaXamlLoader.Load(this);
        }

        private void ResizeBtn_Click(object sender, RoutedEventArgs e)
        {
            if (int.TryParse(_rowsBox.Text, out int r) && int.TryParse(_colsBox.Text, out int c) && r>0 && c>0)
            {
                model.Resize(r, c);
                RenderGrid();
            }
        }

        private async void SaveBtn_Click(object sender, RoutedEventArgs e)
        {
            var dlg = new SaveFileDialog();
            dlg.Filters.Add(new FileDialogFilter() { Name = "JSON", Extensions = { "json" } });
            var path = await dlg.ShowAsync(this);
            if (!string.IsNullOrEmpty(path))
            {
                model.SaveToFile(path);
            }
        }

        private async void LoadBtn_Click(object sender, RoutedEventArgs e)
        {
            var dlg = new OpenFileDialog();
            dlg.Filters.Add(new FileDialogFilter() { Name = "JSON", Extensions = { "json" } });
            dlg.AllowMultiple = false;
            var res = await dlg.ShowAsync(this);
            if (res != null && res.Length > 0)
            {
                var path = res[0];
                var loaded = SpreadsheetModel.LoadFromFile(path);
                model = loaded;
                _rowsBox.Text = model.Rows.ToString();
                _colsBox.Text = model.Cols.ToString();
                RenderGrid();
            }
        }

        private void RenderGrid()
        {
            _gridPanel.Children.Clear();
            int rows = model.Rows;
            int cols = model.Cols;
            bool showValues = _showValuesToggle.IsChecked ?? false;

            var header = new StackPanel { Orientation = Avalonia.Layout.Orientation.Horizontal, Spacing = 4 };
            header.Children.Add(new TextBlock { Text = "", Width = 40 });
            for (int c = 0; c < cols; c++)
            {
                header.Children.Add(new TextBlock { Text = ColIndexToName(c), Width = 120 });
            }
            _gridPanel.Children.Add(header);

            for (int r = 0; r < rows; r++)
            {
                var rowPanel = new StackPanel { Orientation = Avalonia.Layout.Orientation.Horizontal, Spacing = 4 };
                rowPanel.Children.Add(new TextBlock { Text = (r+1).ToString(), Width = 40, VerticalAlignment = Avalonia.Layout.VerticalAlignment.Center });

                for (int c = 0; c < cols; c++)
                {
                    var tb = new TextBox { Width = 120, Tag = (r<<16)|c };
                    string expr = model.GetCell(r,c);
                    if (showValues)
                    {
                        try
                        {
                            var val = SpreadsheetEvaluator.EvaluateExpression(expr, model, r, c);
                            tb.Text = val.ToString();
                        }
                        catch (Exception ex)
                        {
                            tb.Text = $"#ERR: {ex.Message}";
                        }
                    }
                    else
                    {
                        tb.Text = expr;
                    }
                    tb.LostFocus += (s, e) =>
                    {
                        var t = s as TextBox;
                        int tag = (int)t.Tag;
                        int rr = tag >> 16;
                        int cc = tag & 0xFFFF;
                        if (!(_showValuesToggle.IsChecked ?? false))
                        {
                            model.SetCell(rr, cc, t.Text);
                        }
                    };
                    tb.DoubleTapped += (s, e) =>
                    {
                        var t = s as TextBox;
                        int tag = (int)t.Tag;
                        int rr = tag >> 16;
                        int cc = tag & 0xFFFF;
                        var edit = new Window { Title = $"Edit {ColIndexToName(cc)}{rr+1}", Width=400, Height=200 };
                        var stack = new StackPanel { Margin = new Avalonia.Thickness(8) };
                        var editBox = new TextBox { Text = model.GetCell(rr,cc) };
                        var ok = new Button { Content = "OK", HorizontalAlignment = Avalonia.Layout.HorizontalAlignment.Right };
                        ok.Click += (_,__)=> { model.SetCell(rr, cc, editBox.Text); edit.Close(); RenderGrid(); };
                        stack.Children.Add(editBox);
                        stack.Children.Add(ok);
                        edit.Content = stack;
                        edit.ShowDialog(this);
                    };

                    rowPanel.Children.Add(tb);
                }

                _gridPanel.Children.Add(rowPanel);
            }
        }

        private string ColIndexToName(int index)
        {
            string res = "";
            index++;
            while (index > 0)
            {
                int rem = (index-1) % 26;
                res = (char)('A' + rem) + res;
                index = (index-1) / 26;
            }
            return res;
        }
    }
}
