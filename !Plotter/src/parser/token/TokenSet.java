package parser.token;

import java.util.Arrays;
import java.util.StringTokenizer;

public class TokenSet {

	private final String[] tokens;
	private final int start, end, size;

	public final static String splitter = "+-*/^(),";

	public final static boolean ignoredSignes(char in) {
		return true;
	}

	private static String clean(String input){
		return input=input.toLowerCase().trim();
	}
	
	public TokenSet(String input) {
		StringTokenizer s=new StringTokenizer(input, splitter, true);

		this.start = 0;
		this.end = s.countTokens();
		this.size = end - start;
		
		tokens=new String[end];
		for(int i=0;i<end;i++)
			tokens[i]=clean(s.nextToken());
	}

	public TokenSet(String[] tokens) {
		this(tokens, 0, tokens.length);
	}

	private TokenSet(String[] tokens, int start, int end) {
		this.tokens = tokens;
		this.start = start;
		this.end = end;
		this.size = end - start;
	}

	public TokenSet subset(int start, int end) {
		if (end <= 0)
			return new TokenSet(tokens, this.start + start, this.end + end);
		else
			return new TokenSet(tokens, this.start + start, this.start + end);
	}

	public String get(int i) {
		try {
			if (i < 0)
				return tokens[end + i];
			else
				return tokens[start + i];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public boolean fit(int i, String s) {
		return s.equalsIgnoreCase(get(i));
	}

	public int search(String s) {
		for (int i = 0; i < size; i++)
			if (fit(i, s))
				return i;
		return -1;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(tokens, start, end));
	}
}
