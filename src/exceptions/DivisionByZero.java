package exceptions;

public class DivisionByZero extends RuntimeException{
	private String message;
	public DivisionByZero(String msg){
		this.message = msg;
	}
	public String getMessage()
	{
		return this.message;
	}
}
