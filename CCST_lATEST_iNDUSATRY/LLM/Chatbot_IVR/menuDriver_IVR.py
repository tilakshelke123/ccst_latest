




while True:
    print(" *** wELCOME TO THE cHATbOT IVR of Hdfc BANK ***")
    print("Press 1 - For Balance ")
    print("Press 2 - For  Credit card AND sERVICES")
    print("Press 3 - For fOREX card AND sERVICES ") 
    print("Press * - for Exit ")
    
    print("May i KNOW YOUYR NAME :- ")
    name=(input(":-"))
    
    print("Enter the Option :-")
    num = (input(":-"))
    
    if(num =='1'):
        print(" Bal :- 10000/-")
        print("Thank you for contacting us  "+name)
    elif(num =='2'):
        print(" HDFC bank Credit Card ")
        print("Thank you for contacting us  "+name)
    elif(num =='3'):
        print(" Axis Bank  Forex Card ")
        print("Thank you for contacting us  "+name)
    elif(num == '*'):
        break;
        
    else:
        print("something went wrong ")
        print("plz try again !!")
        continue;
    
    