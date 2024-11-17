import java.util.*;
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, member> membersList = new HashMap<>();
        System.out.println("Instructions..");
        System.out.println("    First you have to Input Grandfather family details of Payyan Family for both Appa side and Amma side !");
        System.out.println("    And then Input Known family details one by one !");
        System.out.println("    After Inserting family details, Input Payyan name to find Moraponnu !");
        boolean loop = true;
        while(loop){
            System.out.println("Enter Family details: ");
            System.out.print("Father name: ");
            String fathername = sc.next();
            member m1;
            if(membersList.containsKey(fathername))
            {
                m1 = membersList.get(fathername);
            }    
            else
            {
                m1 = new member(fathername,"male");
                membersList.put(fathername,m1);
            }
            System.out.print("Mother name: ");
            String mothername = sc.next();
            member m2;
            if(membersList.containsKey(mothername))
            {
                m2 = membersList.get(mothername);
            }    
            else
            {
                m2 = new member(mothername,"female");
                membersList.put(mothername,m2);    
            }
            m1.Relationship.put(mothername, "wife");
            m2.Relationship.put(fathername, "husband");
            System.out.print("Sons count: ");
            int sonC = sc.nextInt();
            System.out.print("Daughters count: ");
            int daugC = sc.nextInt();
            for(int i = 0; i < sonC; i++){
                System.out.print("Enter Son name: ");
                String name = sc.next();
                member m = new member(name,"male");
                membersList.put(name,m);
                m1.Relationship.put(name, "son");
                m.Relationship.put(fathername, "father");
            }
            for(int i = 0; i < daugC; i++){
                System.out.print("Enter Daughter name: ");
                String name = sc.next();
                member m = new member(name,"female");
                membersList.put(name,m);
                m1.Relationship.put(name, "daughter");
                m.Relationship.put(fathername, "father");
            }
            System.out.println("Do you want to Add Another Family details (Yes = 1 or No = 0)");
            int choice = sc.nextInt();
            if(choice == 0)
                loop = false;
            else if(choice != 1){
                System.out.println("Wrongly entered.. Program Terminating..");
                return;
            }
        }
        System.out.println("Enter Payyan name to find Morapannu: ");
        String name = sc.next();
        member person = membersList.get(name);
        if(person.gender == "male")
        {
            // System.out.println(GetPersonName(person, "father"));
            member father = membersList.get(GetPersonName(person, "father"));
            member FGfather = membersList.get(GetPersonName(father, "father"));
            // System.out.println(father.name);
            member mother = membersList.get(GetPersonName(father, "wife"));
            member MGfather = membersList.get(GetPersonName(mother, "father"));
            // System.out.println(mother.name);
            System.out.println("Moraponnu List..");
            for(String k:FGfather.Relationship.keySet()){
                if(FGfather.Relationship.get(k).equals("daughter"))
                {
                    member p = membersList.get(k);
                    String Husbandname = GetPersonName(p,"husband");
                    PrintMoraPonnu(membersList.get(Husbandname));
                }
            }
            for(String k:MGfather.Relationship.keySet()){
                if(MGfather.Relationship.get(k).equals("son"))
                {
                    PrintMoraPonnu(membersList.get(k));
                    }
                }
            }
        }
    public static String GetPersonName(member person, String status)
    {
        for(String k: person.Relationship.keySet()){
            if(person.Relationship.get(k).equals(status)){
                return k;
                }
            }
        return "";
        }
    public static void PrintMoraPonnu(member person){
        for(String s:person.Relationship.keySet()){
            if(person.Relationship.get(s).equals("daughter"))
                System.out.println(s);
            }
        }
    }
