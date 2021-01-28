package utils;

public class Count {
    public static void main(String[] args) {
        String[] ones = {"one","two","three","four","five","six","seven","eight","nine",};
        String[] teens = {"ten","eleven", "twelve", "thirteen", "fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
        String[] tens = {"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        String hundredAnd = "hundredand";
        int hundredAndCount = hundredAnd.length();
        int countTo9=0;
        int count10To19=0;
        int countTens=0;
        for (int i=0; i<ones.length; i++){
            countTo9+=ones[i].length();
        }
        System.out.println(countTo9+3);
//        for (int i=0; i<teens.length; i++){
//            count10To19+=teens[i].length();
//        }
//        for (int i=0; i<tens.length; i++){
//            countTens += tens[i].length();
//        }
//        int count20To99 = 10*countTens+(8*countTo9);
//        int countTo99 = countTo9+count10To19+count20To99;
//        int count100to999 = countTo9*100 + countTo99*9 + 7*9 + (99*9*hundredAndCount);
//        int total = countTo99 + count100to999 +11;
//        System.out.println(total);
    }
}