# will auto detech your Main1,Main2,Main3 scripts
# just run bash validate.sh in the file where your Main.java lies
validate() {
    if [ "$1" == "$2" ]; then
        echo "$3 Cleared"
    else
        echo "$3 Failed"
        echo "-----------OUTPUT-----------"
        echo "$1"
        echo "----------------------------"
        echo "----------EXPECTED----------"
        echo "$2"
        echo "----------------------------"
        echo "------------DIFF------------"
        diff <(echo "$1") <(echo "$2")
        echo "----------------------------"
        if [ "$FLAG" != "-c" ]; then
            exit 0
        fi
    fi
}

FLAG=$@

# Level 1 test cases
L1_0_OUT=$(echo -e "1\n0.500\n0.600\n0.700" | java Main1)
L1_0_VAL=$(echo -e "0.500 1 arrives\n0.500 1 serves by server 1\n0.600 2 arrives\n0.600 2 waits at server 1\n0.700 3 arrives\n0.700 3 leaves\n1.500 1 done serving by server 1\n1.500 2 serves by server 1\n2.500 2 done serving by server 1\n[0.450 2 1]")

validate "$L1_0_OUT", "$L1_0_VAL", "L1-0"

L1_1_OUT=$(echo -e "1\n0.500\n0.600\n0.700\n1.500\n1.600\n1.700" | java Main1)
L1_1_VAL=$(echo -e "0.500 1 arrives\n0.500 1 serves by server 1\n0.600 2 arrives\n0.600 2 waits at server 1\n0.700 3 arrives\n0.700 3 leaves\n1.500 1 done serving by server 1\n1.500 2 serves by server 1\n1.500 4 arrives\n1.500 4 waits at server 1\n1.600 5 arrives\n1.600 5 leaves\n1.700 6 arrives\n1.700 6 leaves\n2.500 2 done serving by server 1\n2.500 4 serves by server 1\n3.500 4 done serving by server 1\n[0.633 3 3]")

validate "$L1_1_OUT", "$L1_1_VAL", "L1-1"

L1_2_OUT=$(echo -e "2\n0.500\n0.600\n0.700\n1.500\n1.600\n1.700" | java Main1)
L1_2_VAL=$(echo -e "0.500 1 arrives\n0.500 1 serves by server 1\n0.600 2 arrives\n0.600 2 serves by server 2\n0.700 3 arrives\n0.700 3 waits at server 1\n1.500 1 done serving by server 1\n1.500 3 serves by server 1\n1.500 4 arrives\n1.500 4 waits at server 1\n1.600 2 done serving by server 2\n1.600 5 arrives\n1.600 5 serves by server 2\n1.700 6 arrives\n1.700 6 waits at server 2\n2.500 3 done serving by server 1\n2.500 4 serves by server 1\n2.600 5 done serving by server 2\n2.600 6 serves by server 2\n3.500 4 done serving by server 1\n3.600 6 done serving by server 2\n[0.450 6 0]")

validate "$L1_2_OUT", "$L1_2_VAL", "L1-2"

# Level 2 test cases
L2_0_OUT=$(echo -e "2 1\n0.500 1.000\n0.600 1.000\n0.700 1.000\n1.500 1.000\n1.600 1.000\n1.700 1.000" | java Main2)
L2_0_VAL=$(echo -e "0.500 1 arrives\n0.500 1 serves by server 1\n0.600 2 arrives\n0.600 2 serves by server 2\n0.700 3 arrives\n0.700 3 waits at server 1\n1.500 1 done serving by server 1\n1.500 3 serves by server 1\n1.500 4 arrives\n1.500 4 waits at server 1\n1.600 2 done serving by server 2\n1.600 5 arrives\n1.600 5 serves by server 2\n1.700 6 arrives\n1.700 6 waits at server 2\n2.500 3 done serving by server 1\n2.500 4 serves by server 1\n2.600 5 done serving by server 2\n2.600 6 serves by server 2\n3.500 4 done serving by server 1\n3.600 6 done serving by server 2\n[0.450 6 0]")

validate "$L2_0_OUT", "$L2_0_VAL", "L2-0"

L2_1_OUT=$(echo -e "1 1\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173" | java Main2)
L2_1_VAL=$(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 1\n1.904 3 done serving by server 1\n2.776 4 arrives\n2.776 4 serves by server 1\n2.791 4 done serving by server 1\n3.877 5 arrives\n3.877 5 serves by server 1\n4.031 5 done serving by server 1\n[0.000 5 0]")

validate "$L2_1_OUT", "$L2_1_VAL", "L2-1"

L2_2_OUT=$(echo -e "2 1\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173\n3.909736 0.012659\n9.006390 1.477717\n9.043360 2.593020\n9.105379 0.296847\n9.159629 0.051732" | java Main2)
L2_2_VAL=$(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 1\n1.904 3 done serving by server 1\n2.776 4 arrives\n2.776 4 serves by server 1\n2.791 4 done serving by server 1\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by server 2\n3.922 6 done serving by server 2\n4.031 5 done serving by server 1\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by server 2\n9.105 9 arrives\n9.105 9 waits at server 1\n9.160 10 arrives\n9.160 10 waits at server 2\n10.484 7 done serving by server 1\n10.484 9 serves by server 1\n10.781 9 done serving by server 1\n11.636 8 done serving by server 2\n11.636 10 serves by server 2\n11.688 10 done serving by server 2\n[0.386 10 0]")

validate "$L2_2_OUT", "$L2_2_VAL", "L2-2"

L2_3_OUT=$(echo -e "2 2\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173\n3.909736 0.012659\n9.006390 1.477717\n9.043360 2.593020\n9.105379 0.296847\n9.159629 0.051732" | java Main2)
L2_3_VAL=$(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 1\n1.904 3 done serving by server 1\n2.776 4 arrives\n2.776 4 serves by server 1\n2.791 4 done serving by server 1\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by server 2\n3.922 6 done serving by server 2\n4.031 5 done serving by server 1\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by server 2\n9.105 9 arrives\n9.105 9 waits at server 1\n9.160 10 arrives\n9.160 10 waits at server 1\n10.484 7 done serving by server 1\n10.484 9 serves by server 1\n10.781 9 done serving by server 1\n10.781 10 serves by server 1\n10.833 10 done serving by server 1\n11.636 8 done serving by server 2\n[0.300 10 0]")

validate "$L2_3_OUT", "$L2_3_VAL", "L2-3"

# Level 3 test cases
L3_0_OUT=$(echo -e "2 2 10\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173\n3.909736 0.012659\n9.006390 1.477717\n9.043360 2.593020\n9.105379 0.296847\n9.159629 0.051732\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0" | java Main3)
L3_0_VAL=$(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 1\n1.904 3 done serving by server 1\n2.776 4 arrives\n2.776 4 serves by server 1\n2.791 4 done serving by server 1\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by server 2\n3.922 6 done serving by server 2\n4.031 5 done serving by server 1\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by server 2\n9.105 9 arrives\n9.105 9 waits at server 1\n9.160 10 arrives\n9.160 10 waits at server 1\n10.484 7 done serving by server 1\n10.484 9 serves by server 1\n10.781 9 done serving by server 1\n10.781 10 serves by server 1\n10.833 10 done serving by server 1\n11.636 8 done serving by server 2\n[0.300 10 0]")

validate "$L3_0_OUT", "$L3_0_VAL", "L3-0"

L3_1_OUT=$(echo -e "2 2 10\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173\n3.909736 0.012659\n9.006390 1.477717\n9.043360 2.593020\n9.105379 0.296847\n9.159629 0.051732\n0\n3.138762\n0.847804\n0\n0.848968\n0\n0\n3.863139\n0\n0" | java Main3)
L3_1_VAL=$(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 2\n1.904 3 done serving by server 2\n2.776 4 arrives\n2.776 4 serves by server 2\n2.791 4 done serving by server 2\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by server 2\n3.922 6 done serving by server 2\n4.031 5 done serving by server 1\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by server 2\n9.105 9 arrives\n9.105 9 waits at server 1\n9.160 10 arrives\n9.160 10 waits at server 1\n10.484 7 done serving by server 1\n10.484 9 serves by server 1\n10.781 9 done serving by server 1\n11.636 8 done serving by server 2\n14.644 10 serves by server 1\n14.696 10 done serving by server 1\n[0.686 10 0]")

validate "$L3_1_OUT", "$L3_1_VAL", "L3-1"