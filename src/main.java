public class main{
    public static void main(String[] args) {
        System.out.println("Tabuleiro");
        
    }
}  for (int i = 0; i < vet.length; i++) {
    System.out.println("nomes:  " + i + " " + vet[i]);
}

System.out.println("qual  nome procuras ?");
String nome = System.console().readLine();
System.out.println(nome);

boolean encontrar = false;
for (int i = 0; i < vet.length; i++) {
    String nomeVet = vet[i];
    if (nomeVet.equals(nome)) {
        System.out.println("achou");
        encontrar = true;
        break;
    }
}
if (encontrar == true) {
    System.out.println("Encontrado");
} else {
    System.out.println("nao encontrado");
}

