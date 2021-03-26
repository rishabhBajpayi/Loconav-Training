class Math_{
    private int a,b ;
    Math_(int a,int b){
      this.a = a ;
      this.b = b ;
    }
    void Print(){
      System.out.println("a -> "+a);
      System.out.println("b -> "+b);
    }
    int sum(){
      return a+b ;
    }
    int subtract(){
      return a-b ;
    }
    int multiply(){
      return a*b ;
    }
    int divide(){
      return a/b ;
    }
    int allFor(){
      int res = 0;
      for (int i = (a<b?a:b)+1 ;i < (a<b?b:a) ;i++ )
        res += i ;
      return res ;
    }
    int allWhile(){
      int res = 0;
      int i = (a<b?a:b) +1 ;
      while (i < (a<b?b:a))
        res += i++ ;
      return res ;
    }
    int conditionalOperation(){
      if (a<b)
        return a+b;
      else if (a>b && b==0)
        return a;
      else if (a==b)
        return a*b;
      else
        return a/b;
    }
    public static void main(String args[]){
      Math_ obj = new Math_(4,5);
      obj.Print();
      System.out.println(obj.divide());
    }
}
