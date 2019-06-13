public interface List<T> {
	public void findFirst();

	public void findNext();

	public T retrieve();

	public void update(T e);

	public void insert(T data);

	public void remove();

	public boolean full();

	public boolean empty();

	public boolean last();

	
}
