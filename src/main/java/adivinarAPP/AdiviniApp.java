package adivinarAPP;

public class AdiviniApp {

	private int numeroSelecionado;


	AdiviniApp() {
		numeroSelecionado =generadorNumero();
		
	}

	public boolean comprobarNumero(int _numero) {
		return numeroSelecionado == _numero ? true : false;
	}
	
	public void nuevoJuego(){
		numeroSelecionado=generadorNumero();
		
	}
	public int getNumeroSelecionado() {
		return numeroSelecionado;
	}
	private int generadorNumero() {
		return (int) (Math.random()*((100-1)+1));
	}
	public int generarNuevoNumero() {
		return numeroSelecionado=generadorNumero();
	}
	

}
