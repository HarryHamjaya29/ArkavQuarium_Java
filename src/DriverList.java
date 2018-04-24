import java.util.Random;

public class DriverList {
    public static void main(String[] args){
        List<Integer> listint = new List<>();

        listint.add(1);
        listint.add(2);
        listint.add(3);
        listint.add(4);
        listint.add(5);
        listint.add(6);
        listint.add(7);

        for(int i = 0; i < listint.getSize(); i++) {
            System.out.println(listint.getIdx(i));
        }

        listint.removeIdx(4);

        for(int i = 0; i < listint.getSize(); i++) {
            System.out.println(listint.getIdx(i));
        }
    }
}
