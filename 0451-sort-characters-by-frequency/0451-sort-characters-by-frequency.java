class Solution {
    public String frequencySort(String s) {
      int[] freq=new int[123];
      char[] ans=new char[s.length()];
      for(char c:s.toCharArray()){
        freq[c]++;
      }
      int len=0;
      while(len<s.length()){
        int max=0;
        char maxchar=' ';
        for(int i=0;i<123;i++){
            if(freq[i]>max){
                max=freq[i];
                maxchar=(char) i;
            }
        }
        while(max>0){
            ans[len++]=maxchar;
            max--;
        }
        freq[maxchar]=0;
      }
     return new String(ans);
    }
}