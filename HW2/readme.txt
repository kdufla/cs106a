Davit Akopovi <dakop14@freeuni.edu.ge>
11/15/15

to me, Giorgi 
გამარჯობა, გიგზავნით მეორე დავალების შენიშვნებს/კომენტარებს/რჩევებს.
თუ რაიმე შეკითხვა განიჩნდებათ მომწერეთ ან მნახეთ უნივერსიტეტში.

გველესიანი გიორგი

1) Pyramid
კარგია. რადგან y, j, i მხოლოდ მთელ მნიშვნელობებს იღებენ, მათი ტიპი უნდა იყოს int. ჯობდა შეგექმნა ცვლადი curRowStartXCoord გამოთვლების მეტი დეკომპოზიციისთვის.

2) Target
ფერები ჯობდა კონსტანტებად გაგეტანა. one, two, three -ს ნაცვლად ჯობია bigOval, mediumOval, smallOval მეტი სიცხადისთვის.

3) Program Hierarcy
კარგია. ლეიბლების დამატებისას გამოთვლების ნაწილის გატანა შეიძლებოდა. h, w, l, s არაინფორმატიული სახელებია. ზოგადად მეთოდებს მოქმედებითი სახელები უნდა ჰქონდეთ, მაგ createOval() vs oval().

4) Pythagorean Theorem
გვერდების სიგრძეების შეყვანა ორჯერ გაქვს: ციკლის გარეთაც და ციკლშიც; ეს მცირე, მაგრამ მაინც კოდის გამეორებაა. შემდეგნაირად შეიძლებოდა ამის არიდება:
computeHypothenuse(){
    int a = getNumber();
    int b = getNumber();
    return (Math.sqrt(a*a + b*b))
}

getNumber(){
    print ("Please enter side length");
    int num;
    while(true)
        num = readInt();
            if (num > 0) break;
         print("Invalid input, try again")
    return num;
}

5) Find Range
აქაც არის კოდის გამეორებაა. value -ს გარეშეც შეიძლებოდა დაწერა. ერთ-ერთი ვარიანტი შემდეგნაირია:
    int minValue = Integer.MAX_VALUE;
    int maxValue = Integer.MIN_VALUE;
    while (true){
        int cur = readInt("Enter number you want:");
        if (cur == SPECIAL) break;
        minValue = Math.min(cur, minValue);
        maxValue = Math.max(cur, maxValue);
    }
    println("maximum value is " + maxValue);
    println("minimum value is " + minValue);
 

6) Hailstone
line 15-21: კოდის გამეორება. process უკეთესი სახელი count-ია. მეტი დეკომპოზიცია სასურველი იქნებოდა, მაგალითად, ცალკე n -ის წაკითხვა და ცალკე პროცესის გაშვება.

წინასწარი შეფასება: check+
