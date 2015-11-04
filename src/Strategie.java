public interface Strategy {
	public byte Move();
	public Strategy Next(byte oponent_move);
	public Strategy clone();
}
