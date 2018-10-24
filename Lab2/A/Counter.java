//
//public class Counter{
//
//    private static int num;
//
//    public Counter(int liczba) {
//        this.num = liczba;
//    }
//
//    public void inc(){
//        num++;
//    }
//
//    public void dec(){
//        num--;
//    }
//
//    public int getNum() {
//        return num;
//    }
//
//
//}
package Lab2.A;



public class Counter{

    private static int num;

    public Counter(int liczba) {
        this.num = liczba;
    }

    public synchronized void inc(){
        num++;
    }

    public synchronized void dec(){
        num--;
    }

    public int getNum() {
        return num;
    }


}