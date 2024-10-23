class thue_morse {
    static String complement(String base){
        String complement = "";
        for(int i = 0; i < base.length(); i++){
            if(base.charAt(i) == '1'){
                complement += "0";
            }
            else complement += "1";
        }
        return complement;
    }
    static String thue_morse_sequence(int n){
        String sequence = "0";
        for(int i = 0; i < n; i++){
            if(sequence.length() > n) break;
            sequence += complement(sequence);
        }
        return sequence;
    }
    static void thue_morse_weave(int n){
        String sequence = thue_morse_sequence(n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(sequence.charAt(i) == sequence.charAt(j)){
                    System.out.print("+  ");
                }
                else System.out.print("-  ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        thue_morse_weave(8);
    }
}
