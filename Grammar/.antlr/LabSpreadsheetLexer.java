// Generated from /Users/mac/Desktop/SpreadsheetApp/SpreadsheetApp/Grammar/LabSpreadsheet.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class LabSpreadsheetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, IDENT=13, CELL=14, NUMBER=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "IDENT", "CELL", "NUMBER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'not'", "'^'", "'*'", "'/'", "'+'", "'-'", "'='", 
			"'<'", "'>'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "IDENT", "CELL", "NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LabSpreadsheetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LabSpreadsheet.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0010X\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0004\f=\b\f\u000b\f\f\f>\u0001\r\u0004\rB"+
		"\b\r\u000b\r\f\rC\u0001\r\u0001\r\u0005\rH\b\r\n\r\f\rK\t\r\u0001\u000e"+
		"\u0004\u000eN\b\u000e\u000b\u000e\f\u000eO\u0001\u000f\u0004\u000fS\b"+
		"\u000f\u000b\u000f\f\u000fT\u0001\u000f\u0001\u000f\u0000\u0000\u0010"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010\u0001\u0000\u0005\u0002\u0000AZaz\u0001\u0000"+
		"AZ\u0001\u000019\u0001\u000009\u0003\u0000\t\n\r\r  \\\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0001!\u0001\u0000\u0000\u0000"+
		"\u0003#\u0001\u0000\u0000\u0000\u0005%\u0001\u0000\u0000\u0000\u0007)"+
		"\u0001\u0000\u0000\u0000\t+\u0001\u0000\u0000\u0000\u000b-\u0001\u0000"+
		"\u0000\u0000\r/\u0001\u0000\u0000\u0000\u000f1\u0001\u0000\u0000\u0000"+
		"\u00113\u0001\u0000\u0000\u0000\u00135\u0001\u0000\u0000\u0000\u00157"+
		"\u0001\u0000\u0000\u0000\u00179\u0001\u0000\u0000\u0000\u0019<\u0001\u0000"+
		"\u0000\u0000\u001bA\u0001\u0000\u0000\u0000\u001dM\u0001\u0000\u0000\u0000"+
		"\u001fR\u0001\u0000\u0000\u0000!\"\u0005(\u0000\u0000\"\u0002\u0001\u0000"+
		"\u0000\u0000#$\u0005)\u0000\u0000$\u0004\u0001\u0000\u0000\u0000%&\u0005"+
		"n\u0000\u0000&\'\u0005o\u0000\u0000\'(\u0005t\u0000\u0000(\u0006\u0001"+
		"\u0000\u0000\u0000)*\u0005^\u0000\u0000*\b\u0001\u0000\u0000\u0000+,\u0005"+
		"*\u0000\u0000,\n\u0001\u0000\u0000\u0000-.\u0005/\u0000\u0000.\f\u0001"+
		"\u0000\u0000\u0000/0\u0005+\u0000\u00000\u000e\u0001\u0000\u0000\u0000"+
		"12\u0005-\u0000\u00002\u0010\u0001\u0000\u0000\u000034\u0005=\u0000\u0000"+
		"4\u0012\u0001\u0000\u0000\u000056\u0005<\u0000\u00006\u0014\u0001\u0000"+
		"\u0000\u000078\u0005>\u0000\u00008\u0016\u0001\u0000\u0000\u00009:\u0005"+
		",\u0000\u0000:\u0018\u0001\u0000\u0000\u0000;=\u0007\u0000\u0000\u0000"+
		"<;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000><\u0001\u0000\u0000"+
		"\u0000>?\u0001\u0000\u0000\u0000?\u001a\u0001\u0000\u0000\u0000@B\u0007"+
		"\u0001\u0000\u0000A@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000"+
		"CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000"+
		"\u0000EI\u0007\u0002\u0000\u0000FH\u0007\u0003\u0000\u0000GF\u0001\u0000"+
		"\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001"+
		"\u0000\u0000\u0000J\u001c\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000LN\u0007\u0003\u0000\u0000ML\u0001\u0000\u0000\u0000NO\u0001\u0000"+
		"\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000P\u001e"+
		"\u0001\u0000\u0000\u0000QS\u0007\u0004\u0000\u0000RQ\u0001\u0000\u0000"+
		"\u0000ST\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0006\u000f\u0000\u0000W \u0001"+
		"\u0000\u0000\u0000\u0006\u0000>CIOT\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}