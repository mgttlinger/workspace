package parser;

import parser.operation.ParserAddition;
import parser.operation.ParserDivision;
import parser.operation.ParserMultiplication;
import parser.operation.ParserPower;
import parser.operation.ParserSubtraction;

public class ParserOperations extends ParserSet {

	public ParserOperations(){
		super(new ParserAddition(), new ParserSubtraction(), new ParserMultiplication(), new ParserDivision(), new ParserPower());
	}
}
