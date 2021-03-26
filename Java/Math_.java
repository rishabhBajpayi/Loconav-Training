class Math_{
    private float a,b ;
    Math_(float a,float b){
      this.a = a ;
      this.b = b ;
    }
    void Print(){
      System.out.println("a -> "+a);
      System.out.println("b -> "+b);
    }
    float sum(){
      return a+b ;
    }
    float subtract(){
      return a-b ;
    }
    float multiply(){
      return a*b ;
    }
    float divide(){
      return a/b ;
    }
    int allFor(){
      int res = 0;
      for (int i = (int)a+1 ;i < b ;i++ )
        res += i ;
      return res ;
    }
    int allWhile(){
      int res = 0;
      int i = (int)a+1 ;
      while (i < b)
        res += i++ ;
      return res ;
    }
    float conditionalOperation(){
      float res;
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