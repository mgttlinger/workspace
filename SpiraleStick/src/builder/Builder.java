package builder;

public abstract class Builder<T> implements IBuilder<T> {
	private boolean changed=true;
	private T instance=null;
	
	protected void changed(){
		changed=true;
	}
	
	abstract protected T createInstance(); 
	
	public T getInstance(){
		if(changed)
			instance=createInstance();
		return instance;
	}
	
}
