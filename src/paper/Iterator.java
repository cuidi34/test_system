package paper;

public interface Iterator<O> {
	
	public boolean hasNext();
	public <O> O next();
}
