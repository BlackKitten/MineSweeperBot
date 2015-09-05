package data;

public interface Observable {
	public void addObservers(NeighborObserver o);
	public void notifyObservers();
}
