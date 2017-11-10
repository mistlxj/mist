package e.interview.netEase;


public class Test1 {
    public static void main(String[] args){
        System.out.println("aa:" + aa());
    }
    public static int aa(){
        int a = 1;
        try{
            return a;                           //(1)
        }finally{
            a = 3;
            System.out.println("a: "+ a);
            //return a;                         //(2)
        }
    }
}
