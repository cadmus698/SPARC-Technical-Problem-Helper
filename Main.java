import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("First move:\n");
        int num = in.nextInt();
        ArrayList<Integer> game = new ArrayList<>();
        game.add(0);
        int turn = 1;

        while(num != 1){
            game.add(num);
            System.out.println((turn % 2 == 1 ? "B" : "A") + "'s turn:\n");
            System.out.println("Options: " + getOptions(game));
            num = in.nextInt();
            turn++;
        }
        System.out.println((turn % 2 == 1 ? "B" : "A") + " wins");
//        findUncovered();
    }

    public static ArrayList<Integer> getOptions(ArrayList<Integer> game){
        ArrayList<Integer> toRet = new ArrayList<>();
        int last = game.get(game.size() - 1);
        int secLast = game.get(game.size() - 2);
        for(int i : factors(last)){
            if(!game.contains(i)){
                toRet.add(i);
            }
        }
        for(int j : factors(secLast)){
            if(factors(j).contains(last) && !game.contains(j)){
                toRet.add(j);
            }
        }
        Collections.sort(toRet);
        return toRet;
    }

    public static ArrayList<Integer> factors(int n){
        ArrayList<Integer> toRet = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                toRet.add(i);
                toRet.add(n/i);
            }
        }
        Collections.sort(toRet);
        return toRet;
    }

    public static void findUncovered(){
        ArrayList<Integer> toRet = new ArrayList<>();
        for(int i = 1; i < 10000; i += 2){
            if(!isPrime(i) && testPowerAspect(i)){
                toRet.add(i);
            }
        }
        System.out.println(toRet);
    }

    public static boolean testPowerAspect(int n){
        for(int i = 1; i <= Math.sqrt(n); i++){
            if(testPower(n, i)){
                return false;
            }
        }
        return true;
    }

    public static boolean test(int n){
        if(factors(n).size() == 2){
            return false;
        }
        ArrayList<Integer> factors = factors(n);
        ArrayList<Integer> primeFactors = new ArrayList<>();
        ArrayList<Integer> candidates = new ArrayList<>();
        for(int i : factors){
            if(isPrime(i)){
                primeFactors.add(i);
            }
        }
        for(int p : primeFactors){
            boolean flag = true;
            for(int j : factors){
                if(j != 1 && j != n && j != p && factors(j).contains(p)){
                    flag = false;
                }
            }
            if(flag){
                candidates.add(p);
            }
        }
        return candidates.size() == 0;
    }

    public static boolean testPower(int a, int b){
        if(a == b){
            return true;
        }
        else if(a % b != 0 || b == 1){
            return false;
        }
        else{
            return testPower(a/b,b);
        }
    }

    public static boolean isPrime(int n){
        return factors(n).size() == 2;
    }

    public static int numPrimeFactors(int n){
        ArrayList<Integer> primeFactors = new ArrayList<>();
        for(int i : factors(n)){
            if(isPrime(i) && i != 1 && i != n){
                primeFactors.add(i);
            }
        }
        System.out.println(primeFactors);
        return primeFactors.size();
    }
}
