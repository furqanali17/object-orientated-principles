
public class Module {

    //---------------------------------------
    String moduleTitle;
    int crn;
    boolean evaluation;
    //---------------------------------------

    //---------------------------------------

    public Module(String moduleTitle, int crn, boolean evaluation) {
        this.moduleTitle = moduleTitle;

        this.evaluation = evaluation;
        /* declaring if condition for maintaining range of the CRN, declared in constructor since
        get method is only used for return */
        if (crn <= 10000 || crn >= 99999){
            this.crn = 10000;
        }else {
            this.crn = crn;
        }
    }

    //---------------------------------------

    //---------------------------------------

    public String getTitle() {
        return moduleTitle;
    }

    public int getCRN() {
        return crn;
    }

    public boolean getIsCA() {
        return evaluation;
    }

    //---------------------------------------

    //---------------------------------------

    public void setTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public void setCRN(int crn) {
        this.crn = crn;
        //if condition for setting the range of the crn number
        if (crn <= 10000 || crn >= 99999){
            this.crn = 10000;
        }
    }

    public void setIsCA(boolean evaluation) {
        this.evaluation = evaluation;
    }

    //---------------------------------------

    //---------------------------------------
    public int sumOfCRNDigits() {
        //getting the sum of individual digits in CRN by taking the modulus of the number and dividing it by itself
        int sum = 0;
        while (crn > 0) {
            sum = sum + crn % 10;
            crn = crn / 10;
        }
        return sum;
    }

    public int numVowelsOnTitle() {
        /* getting the vowels in the title by using a count and every time a character matches the vowel
        the counter gets incremented by 1 */
        int vowels = 0;
        //setting it to lowercase as some uppercase letters are also vowels
        moduleTitle = moduleTitle.toLowerCase();
        for (int i = 0; i < moduleTitle.length(); i++) {
            char ch = moduleTitle.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            }
        }
        return vowels;
    }

    public void printModuleInfo(int i) {
        //using switch to assign each part of object as a case and printing them
        switch (i){
            case 1:
                System.out.println(this.moduleTitle);
                break;
            case 2:
                System.out.println(this.crn);
                break;
            //turning the boolean to text by using if condition and printing desired result
            case 3:
                if (this.evaluation){
                    System.out.println("Assignment-based");
                }else {
                    System.out.println("Exam-based");
                }
                break;
            //using the default case for when none of the 3 cases are applicable it breaks the switch
            default:
                break;
        }
    }
    //---------------------------------------
}