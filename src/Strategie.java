public interface Strategie{
	public byte Coup();
	public Strategie Next(byte coupadversaire);
	public Strategie clone();
}
