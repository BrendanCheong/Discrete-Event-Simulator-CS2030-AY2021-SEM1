# will auto detech your Main1,Main2,Main3,Main4 scripts
# just run bash validate.sh in the folder where your Main.java lies
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

# Level 4 test cases
L4_0_OUT=$(echo -e "2 0 2 20\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173\n3.909736 0.012659\n9.00639 1.477717\n9.04336 2.59302\n9.105379 0.296847\n9.159629 0.368667\n9.224613 0.051732\n10.147993 3.489595\n11.204932 0.006333\n12.428914 0.160532\n13.109177 2.86915\n15.263626 0.576351\n15.524295 0.89579\n15.939973 1.04302\n17.793096 3.007573\n18.765423 0.74237\n0\n3.138762\n0.847804\n0\n0.848968\n0\n0\n3.863139\n0\n0\n25.460873\n0\n0\n0\n0\n36.96365\n0\n3.577445\n2.169913\n0" | java Main4)

L4_0_VAL=$(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 2\n1.904 3 done serving by server 2\n2.776 4 arrives\n2.776 4 serves by server 2\n2.791 4 done serving by server 2\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by server 2\n3.922 6 done serving by server 2\n4.031 5 done serving by server 1\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by server 2\n9.105 9 arrives\n9.105 9 waits at server 1\n9.160 10 arrives\n9.160 10 waits at server 1\n9.225 11 arrives\n9.225 11 waits at server 2\n10.148 12 arrives\n10.148 12 waits at server 2\n10.484 7 done serving by server 1\n10.484 9 serves by server 1\n10.781 9 done serving by server 1\n11.205 13 arrives\n11.205 13 waits at server 1\n11.636 8 done serving by server 2\n11.636 11 serves by server 2\n11.688 11 done serving by server 2\n11.688 12 serves by server 2\n12.429 14 arrives\n12.429 14 waits at server 2\n13.109 15 arrives\n13.109 15 waits at server 2\n14.644 10 serves by server 1\n15.013 10 done serving by server 1\n15.178 12 done serving by server 2\n15.178 14 serves by server 2\n15.264 16 arrives\n15.264 16 waits at server 1\n15.338 14 done serving by server 2\n15.338 15 serves by server 2\n15.524 17 arrives\n15.524 17 waits at server 2\n15.940 18 arrives\n15.940 18 waits at server 2\n17.793 19 arrives\n17.793 19 leaves\n18.207 15 done serving by server 2\n18.207 17 serves by server 2\n18.765 20 arrives\n18.765 20 waits at server 2\n19.103 17 done serving by server 2\n19.103 18 serves by server 2\n20.146 18 done serving by server 2\n40.474 13 serves by server 1\n40.480 13 done serving by server 1\n40.480 16 serves by server 1\n41.056 16 done serving by server 1\n57.110 20 serves by server 2\n57.852 20 done serving by server 2\n[6.025 19 1]")

validate "$L4_0_OUT", "$L4_0_VAL", "L4-0"

L4_1_OUT = $(echo -e "2 1 2 20\n0 0.313141\n0.313508 0.103753\n1.204909 0.699522\n2.776498 0.014224\n3.876961 0.154173\n3.909736 0.012659\n9.00639 1.477717\n9.04336 2.59302\n9.105379 0.296847\n9.159629 3.489595\n9.224613 0.89579\n10.147993 0.051732\n11.204932 0.368667\n12.428914 0.160532\n13.109177 2.86915\n15.263626 1.04302\n15.524295 0.006333\n15.939973 0.576351\n17.793096 0.74237\n18.765423 3.007573\n0\n3.138762\n0.847804\n0\n0.848968\n0\n0\n3.863139\n0\n0\n25.460873\n0\n0\n0\n0\n36.96365\n0\n3.577445\n2.169913\n0" | java Main4)

L4_1_VAL = $(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.313 1 done serving by server 1\n0.314 2 arrives\n0.314 2 serves by server 1\n0.417 2 done serving by server 1\n1.205 3 arrives\n1.205 3 serves by server 2\n1.904 3 done serving by server 2\n2.776 4 arrives\n2.776 4 serves by server 2\n2.791 4 done serving by server 2\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by server 2\n3.922 6 done serving by server 2\n4.031 5 done serving by server 1\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by server 2\n9.105 9 arrives\n9.105 9 serves by self-check 3\n9.160 10 arrives\n9.160 10 waits at server 1\n9.225 11 arrives\n9.225 11 waits at server 1\n9.402 9 done serving by self-check 3\n10.148 12 arrives\n10.148 12 serves by self-check 3\n10.200 12 done serving by self-check 3\n10.484 7 done serving by server 1\n10.484 10 serves by server 1\n11.205 13 arrives\n11.205 13 serves by self-check 3\n11.574 13 done serving by self-check 3\n11.636 8 done serving by server 2\n12.429 14 arrives\n12.429 14 serves by self-check 3\n12.589 14 done serving by self-check 3\n13.109 15 arrives\n13.109 15 serves by self-check 3\n13.974 10 done serving by server 1\n13.974 11 serves by server 1\n14.869 11 done serving by server 1\n15.264 16 arrives\n15.264 16 serves by server 1\n15.524 17 arrives\n15.524 17 serves by server 2\n15.531 17 done serving by server 2\n15.940 18 arrives\n15.940 18 waits at server 1\n15.978 15 done serving by self-check 3\n16.307 16 done serving by server 1\n16.307 18 serves by server 1\n16.883 18 done serving by server 1\n17.793 19 arrives\n17.793 19 serves by server 1\n18.535 19 done serving by server 1\n18.765 20 arrives\n18.765 20 serves by server 1\n21.773 20 done serving by server 1\n[0.322 20 0]")

validate "$L4_1_OUT", "$L4_1_VAL", "L4-1"

L4_2_OUT = $(echo -e "1 2 2 20\n0 3.131408\n0.313508 1.037533\n1.204909 6.995223\n2.776498 0.142237\n3.876961 1.541726\n3.909736 0.126590\n9.00639 14.777172\n9.04336 25.930199\n9.105379 2.968470\n9.159629 3.686675\n9.224613 1.605316\n10.147993 0.517321\n11.204932 34.895950\n12.428914 28.691500\n13.109177 8.957896\n15.263626 1.043020\n15.524295 0.006333\n15.939973 0.576351\n17.793096 0.742370\n18.765423 3.007573\n0\n3.138762\n0.847804\n0\n0.848968\n0\n0\n3.863139\n0\n0\n25.460873\n0\n0\n0\n0\n36.96365\n0\n3.577445\n2.169913\n0" | java Main4)

L4_2_VAL = $(echo -e "0.000 1 arrives\n0.000 1 serves by server 1\n0.314 2 arrives\n0.314 2 serves by self-check 2\n1.205 3 arrives\n1.205 3 serves by self-check 3\n1.351 2 done serving by self-check 2\n2.776 4 arrives\n2.776 4 serves by self-check 2\n2.919 4 done serving by self-check 2\n3.131 1 done serving by server 1\n3.877 5 arrives\n3.877 5 serves by server 1\n3.910 6 arrives\n3.910 6 serves by self-check 2\n4.036 6 done serving by self-check 2\n5.419 5 done serving by server 1\n8.200 3 done serving by self-check 3\n9.006 7 arrives\n9.006 7 serves by server 1\n9.043 8 arrives\n9.043 8 serves by self-check 2\n9.105 9 arrives\n9.105 9 serves by self-check 3\n9.160 10 arrives\n9.160 10 waits at server 1\n9.225 11 arrives\n9.225 11 waits at server 1\n10.148 12 arrives\n10.148 12 waits at self-check 2\n11.205 13 arrives\n11.205 13 waits at self-check 2\n12.074 9 done serving by self-check 3\n12.074 12 serves by self-check 3\n12.429 14 arrives\n12.429 14 waits at self-check 2\n12.591 12 done serving by self-check 3\n12.591 13 serves by self-check 3\n13.109 15 arrives\n13.109 15 waits at self-check 2\n15.264 16 arrives\n15.264 16 leaves\n15.524 17 arrives\n15.524 17 leaves\n15.940 18 arrives\n15.940 18 leaves\n17.793 19 arrives\n17.793 19 leaves\n18.765 20 arrives\n18.765 20 leaves\n23.784 7 done serving by server 1\n24.631 10 serves by server 1\n28.318 10 done serving by server 1\n28.318 11 serves by server 1\n29.923 11 done serving by server 1\n34.974 8 done serving by self-check 2\n34.974 14 serves by self-check 2\n47.487 13 done serving by self-check 3\n47.487 15 serves by self-check 3\n56.445 15 done serving by self-check 3\n63.665 14 done serving by self-check 2\n[6.320 15 5]")

validate "$L4_2_OUT", "$L4_2_VAL", "L4-2"