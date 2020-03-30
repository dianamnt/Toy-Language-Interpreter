package exceptions;

public class VariableNotDefined extends RuntimeException{
	private String message;
	public VariableNotDefined(String msg){
		this.message = msg;
	}
	public String getMessage()
	{
		return this.message;
	}
}
