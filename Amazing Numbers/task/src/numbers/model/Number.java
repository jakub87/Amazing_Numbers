package numbers.model;

public class Number {
    private long number;
    private boolean even;
    private boolean odd;
    private boolean buzz;
    private boolean duck;
    private boolean palindromic;
    private boolean gapful;
    private boolean spy;
    private boolean square;
    private boolean sunny;
    private boolean jumping;
    private boolean sad;
    private boolean happy;
    private String stringNumber;

    public Number(long number) {
        this.number = number;
        this.jumping = true;
        this.stringNumber = String.valueOf(number);
    }

    public boolean isEven() {
        return even;
    }

    public boolean isOdd() {
        return odd;
    }

    public boolean isBuzz() {
        return buzz;
    }

    public boolean isDuck() {
        return duck;
    }

    public boolean isPalindromic() {
        return palindromic;
    }

    public boolean isGapful() {
        return gapful;
    }

    public boolean isSpy() {
        return spy;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isSquare() {
        return square;
    }

    public boolean isSunny() {
        return sunny;
    }

    public boolean isSad() {
        return sad;
    }

    public boolean isHappy() {
        return happy;
    }

    public void diagnose() {
            setEvenOrOdd();
            setBuzzNumber();
            setDuck();
            setPalindromic();
            setGapful();
            setSpy();
            setSquare();
            setSunny();
            setJumping();
            setHappy();
    }

    private void setHappy() {
        long tempNumber = number;
        while(true) {
            long sum = 0;
            do {
                sum += (int) Math.pow(tempNumber % 10, 2);
                tempNumber = tempNumber / 10;
            } while (tempNumber != 0);
            tempNumber = sum;
            if (tempNumber == 1) {
                happy = true;
                break;
            } else if (tempNumber == 4) {
                sad = true;
                break;
            }
        }
    }

    private void setJumping() {
        for (int i = 0; i < stringNumber.length() - 1; i++) {
            if (Math.abs(stringNumber.charAt(i) - stringNumber.charAt(i + 1)) != 1) {
                this.jumping = false;
                break;
            }
        }
    }

    private void setSpy() {
        long sumAdd = 0;
        long sumProduct = 1;
        for (int i = 0; i < stringNumber.length(); i++) {
            long tempNumber = Long.parseLong(String.valueOf(stringNumber.charAt(i)));
            sumAdd += tempNumber;
            sumProduct *= tempNumber;
        }
        if (sumAdd == sumProduct) {
            this.spy = true;
        }
    }

    private void setGapful() {
        if (stringNumber.length() > 2) {
            char firstDigit = stringNumber.charAt(0);
            char lastDigit = stringNumber.charAt(stringNumber.length() - 1);
            long reminder = Long.parseLong(firstDigit + "" + lastDigit);
            if (this.number % reminder == 0) {
                this.gapful = true;
            }
        }
    }

    private void setSunny() {
        double numberSqrt = Math.sqrt(this.number + 1);
        if (numberSqrt == (int) Math.sqrt(this.number + 1)) {
            this.sunny = true;
        }
    }

    private void setSquare() {
        double numberSqrt = Math.sqrt(this.number);
        if (numberSqrt == (int) Math.sqrt(this.number)) {
            this.square = true;
        }
    }

    private void setPalindromic() {
        String reverseNumber = String.valueOf(new StringBuilder(stringNumber).reverse());
        if (reverseNumber.equals(stringNumber)) {
            this.palindromic = true;
        }
    }

    private void setDuck() {
        if (stringNumber.contains("0")) {
            this.duck = true;
        }
    }

    private void setEvenOrOdd() {
        if (this.number % 2 == 0) {
            this.even = true;
        } else {
            this.odd = true;
        }
    }

    private void setBuzzNumber() {
        long lastDigit = this.number % 10;
        if (this.number % 7 == 0 || lastDigit == 7 ) {
            this.buzz = true;
        }
    }

    public String toStringList() {
        String isBuzz = this.buzz ? " buzz" : "";
        String isDuck = this.duck ? " duck" : "";
        String isPalindromic = this.palindromic ? " palindromic" : "";
        String isGapful = this.gapful ? " gapful" : "";
        String isEven = this.even ? " even" : "";
        String isOdd = this.odd ? " odd" : "";
        String isSpy = this.spy ? " spy" : "";
        String isSunny = this.sunny ? " sunny" : "";
        String isSquare = this.square ? " square" : "";
        String isJumping = this.jumping ? " jumping" : "";
        String isHappy = this.happy ? " happy" : "";
        String isSad = this.sad ? " sad" : "";
        return this.number + " is" + isBuzz + isDuck + isPalindromic + isGapful + isEven + isOdd + isSpy + isSunny + isSquare + isJumping + isHappy + isSad;
    }

    @Override
    public String toString() {
        return "\nProperties of " + this.number + "\n"
                + "even: " + this.even + "\n"
                + "odd: " + this.odd + "\n"
                + "buzz: " + this.buzz + "\n"
                + "duck: " + this.duck + "\n"
                + "palindromic: " + this.palindromic + "\n"
                + "gapful: " + this.gapful + "\n"
                + "spy: " + this.spy + "\n"
                + "sunny: " + this.sunny + "\n"
                + "square: " + this.square + "\n"
                + "jumping: " + this.jumping + "\n"
                + "happy: " + this.happy + "\n"
                + "sad: " + this.sad + "\n";
    }
}
