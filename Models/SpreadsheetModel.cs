using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;

namespace SpreadsheetApp.Models
{
    public class SpreadsheetModel
    {
        public int Rows { get; set; }
        public int Cols { get; set; }
        public List<string> Cells { get; set; } = new List<string>();

        public SpreadsheetModel() { }

        public SpreadsheetModel(int rows, int cols)
        {
            Rows = rows; Cols = cols;
            Cells = new List<string>(new string[rows * cols]);
            for (int i = 0; i < Cells.Count; i++) Cells[i] = "";
        }

        public void Resize(int newRows, int newCols)
        {
            var newCells = new List<string>(new string[newRows * newCols]);
            for (int r = 0; r < newRows; r++)
            for (int c = 0; c < newCols; c++)
            {
                if (r < Rows && c < Cols)
                    newCells[r * newCols + c] = Cells[r * Cols + c];
                else
                    newCells[r * newCols + c] = "";
            }
            Rows = newRows; Cols = newCols; Cells = newCells;
        }

        public string GetCell(int r, int c)
        {
            if (r < 0 || c < 0 || r >= Rows || c >= Cols) return "";
            return Cells[r * Cols + c] ?? "";
        }
        public void SetCell(int r, int c, string text)
        {
            if (r < 0 || c < 0 || r >= Rows || c >= Cols) return;
            Cells[r * Cols + c] = text ?? "";
        }

        public void SaveToFile(string path)
        {
            var json = JsonSerializer.Serialize(this, new JsonSerializerOptions { WriteIndented = true });
            File.WriteAllText(path, json);
        }

        public static SpreadsheetModel LoadFromFile(string path)
        {
            var json = File.ReadAllText(path);
            return JsonSerializer.Deserialize<SpreadsheetModel>(json);
        }
    }
}
