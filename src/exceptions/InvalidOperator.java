package exceptions;

public class InvalidOperator extends RuntimeException{
	private String message;
	public InvalidOperator(String msg){
		this.message = msg;
	}
	public String getMessage()
	{
		return this.message;
	}
}
