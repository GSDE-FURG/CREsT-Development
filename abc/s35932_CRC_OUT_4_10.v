//Converted to Combinational (Partial output: CRC_OUT_4_10) , Module name: s35932_CRC_OUT_4_10 , Timestamp: 2018-12-03T15:51:10.927821 
module s35932_CRC_OUT_4_10 ( RESET, WX7344, WX7346, WX7348, WX7350, WX7352, WX7354, WX7356, WX7358, WX7302, WX7360, WX7304, WX7362, WX7306, WX7364, WX7308, WX7310, WX7312, WX7314, WX7316, WX7318, WX7320, WX7322, WX7324, WX7326, WX7328, WX7330, WX7332, WX7334, WX7336, WX7338, WX7340, WX7342, CRC_OUT_4_10 );
input RESET, WX7344, WX7346, WX7348, WX7350, WX7352, WX7354, WX7356, WX7358, WX7302, WX7360, WX7304, WX7362, WX7306, WX7364, WX7308, WX7310, WX7312, WX7314, WX7316, WX7318, WX7320, WX7322, WX7324, WX7326, WX7328, WX7330, WX7332, WX7334, WX7336, WX7338, WX7340, WX7342;
output CRC_OUT_4_10;
wire n5827, n9225, CRC_OUT_4_9, n9223, CRC_OUT_4_8, n9221, CRC_OUT_4_7, n9219, CRC_OUT_4_6, n9217, CRC_OUT_4_5, n9215, CRC_OUT_4_4, n9213, CRC_OUT_4_3, n9212, n9210, CRC_OUT_4_31, CRC_OUT_4_2, n9269, n9208, CRC_OUT_4_30, CRC_OUT_4_1, n9267, n9206, CRC_OUT_4_29, CRC_OUT_4_0, n9265, n9204, CRC_OUT_4_28, n9263, CRC_OUT_4_27, n9261, CRC_OUT_4_26, n9259, CRC_OUT_4_25, n9257, CRC_OUT_4_24, n9255, CRC_OUT_4_23, n9253, CRC_OUT_4_22, n9251, CRC_OUT_4_21, n9249, CRC_OUT_4_20, n9247, CRC_OUT_4_19, n9245, CRC_OUT_4_18, n9243, CRC_OUT_4_17, n9241, CRC_OUT_4_16, n9239, CRC_OUT_4_15, n9238, n9236, CRC_OUT_4_14, n9234, CRC_OUT_4_13, n9232, CRC_OUT_4_12, n9230, CRC_OUT_4_11, n9228, n9227;
NOR2X1   g3495(.A(n9225), .B(n5827), .Y(CRC_OUT_4_10));
INVX1    g0288(.A(RESET), .Y(n5827));
XOR2X1   g3494(.A(CRC_OUT_4_9), .B(WX7344), .Y(n9225));
NOR2X1   g3493(.A(n9223), .B(n5827), .Y(CRC_OUT_4_9));
XOR2X1   g3492(.A(CRC_OUT_4_8), .B(WX7346), .Y(n9223));
NOR2X1   g3491(.A(n9221), .B(n5827), .Y(CRC_OUT_4_8));
XOR2X1   g3490(.A(CRC_OUT_4_7), .B(WX7348), .Y(n9221));
NOR2X1   g3489(.A(n9219), .B(n5827), .Y(CRC_OUT_4_7));
XOR2X1   g3488(.A(CRC_OUT_4_6), .B(WX7350), .Y(n9219));
NOR2X1   g3487(.A(n9217), .B(n5827), .Y(CRC_OUT_4_6));
XOR2X1   g3486(.A(CRC_OUT_4_5), .B(WX7352), .Y(n9217));
NOR2X1   g3485(.A(n9215), .B(n5827), .Y(CRC_OUT_4_5));
XOR2X1   g3484(.A(CRC_OUT_4_4), .B(WX7354), .Y(n9215));
NOR2X1   g3483(.A(n9213), .B(n5827), .Y(CRC_OUT_4_4));
XOR2X1   g3482(.A(n9212), .B(CRC_OUT_4_3), .Y(n9213));
NOR2X1   g3480(.A(n9210), .B(n5827), .Y(CRC_OUT_4_3));
XOR2X1   g3481(.A(CRC_OUT_4_31), .B(WX7356), .Y(n9212));
XOR2X1   g3479(.A(CRC_OUT_4_2), .B(WX7358), .Y(n9210));
NOR2X1   g3539(.A(n9269), .B(n5827), .Y(CRC_OUT_4_31));
NOR2X1   g3478(.A(n9208), .B(n5827), .Y(CRC_OUT_4_2));
XOR2X1   g3538(.A(CRC_OUT_4_30), .B(WX7302), .Y(n9269));
XOR2X1   g3477(.A(CRC_OUT_4_1), .B(WX7360), .Y(n9208));
NOR2X1   g3537(.A(n9267), .B(n5827), .Y(CRC_OUT_4_30));
NOR2X1   g3476(.A(n9206), .B(n5827), .Y(CRC_OUT_4_1));
XOR2X1   g3536(.A(CRC_OUT_4_29), .B(WX7304), .Y(n9267));
XOR2X1   g3475(.A(CRC_OUT_4_0), .B(WX7362), .Y(n9206));
NOR2X1   g3535(.A(n9265), .B(n5827), .Y(CRC_OUT_4_29));
NOR2X1   g3474(.A(n9204), .B(n5827), .Y(CRC_OUT_4_0));
XOR2X1   g3534(.A(CRC_OUT_4_28), .B(WX7306), .Y(n9265));
XOR2X1   g3473(.A(CRC_OUT_4_31), .B(WX7364), .Y(n9204));
NOR2X1   g3533(.A(n9263), .B(n5827), .Y(CRC_OUT_4_28));
XOR2X1   g3532(.A(CRC_OUT_4_27), .B(WX7308), .Y(n9263));
NOR2X1   g3531(.A(n9261), .B(n5827), .Y(CRC_OUT_4_27));
XOR2X1   g3530(.A(CRC_OUT_4_26), .B(WX7310), .Y(n9261));
NOR2X1   g3529(.A(n9259), .B(n5827), .Y(CRC_OUT_4_26));
XOR2X1   g3528(.A(CRC_OUT_4_25), .B(WX7312), .Y(n9259));
NOR2X1   g3527(.A(n9257), .B(n5827), .Y(CRC_OUT_4_25));
XOR2X1   g3526(.A(CRC_OUT_4_24), .B(WX7314), .Y(n9257));
NOR2X1   g3525(.A(n9255), .B(n5827), .Y(CRC_OUT_4_24));
XOR2X1   g3524(.A(CRC_OUT_4_23), .B(WX7316), .Y(n9255));
NOR2X1   g3523(.A(n9253), .B(n5827), .Y(CRC_OUT_4_23));
XOR2X1   g3522(.A(CRC_OUT_4_22), .B(WX7318), .Y(n9253));
NOR2X1   g3521(.A(n9251), .B(n5827), .Y(CRC_OUT_4_22));
XOR2X1   g3520(.A(CRC_OUT_4_21), .B(WX7320), .Y(n9251));
NOR2X1   g3519(.A(n9249), .B(n5827), .Y(CRC_OUT_4_21));
XOR2X1   g3518(.A(CRC_OUT_4_20), .B(WX7322), .Y(n9249));
NOR2X1   g3517(.A(n9247), .B(n5827), .Y(CRC_OUT_4_20));
XOR2X1   g3516(.A(CRC_OUT_4_19), .B(WX7324), .Y(n9247));
NOR2X1   g3515(.A(n9245), .B(n5827), .Y(CRC_OUT_4_19));
XOR2X1   g3514(.A(CRC_OUT_4_18), .B(WX7326), .Y(n9245));
NOR2X1   g3513(.A(n9243), .B(n5827), .Y(CRC_OUT_4_18));
XOR2X1   g3512(.A(CRC_OUT_4_17), .B(WX7328), .Y(n9243));
NOR2X1   g3511(.A(n9241), .B(n5827), .Y(CRC_OUT_4_17));
XOR2X1   g3510(.A(CRC_OUT_4_16), .B(WX7330), .Y(n9241));
NOR2X1   g3509(.A(n9239), .B(n5827), .Y(CRC_OUT_4_16));
XOR2X1   g3508(.A(n9238), .B(CRC_OUT_4_15), .Y(n9239));
NOR2X1   g3506(.A(n9236), .B(n5827), .Y(CRC_OUT_4_15));
XOR2X1   g3507(.A(CRC_OUT_4_31), .B(WX7332), .Y(n9238));
XOR2X1   g3505(.A(CRC_OUT_4_14), .B(WX7334), .Y(n9236));
NOR2X1   g3504(.A(n9234), .B(n5827), .Y(CRC_OUT_4_14));
XOR2X1   g3503(.A(CRC_OUT_4_13), .B(WX7336), .Y(n9234));
NOR2X1   g3502(.A(n9232), .B(n5827), .Y(CRC_OUT_4_13));
XOR2X1   g3501(.A(CRC_OUT_4_12), .B(WX7338), .Y(n9232));
NOR2X1   g3500(.A(n9230), .B(n5827), .Y(CRC_OUT_4_12));
XOR2X1   g3499(.A(CRC_OUT_4_11), .B(WX7340), .Y(n9230));
NOR2X1   g3498(.A(n9228), .B(n5827), .Y(CRC_OUT_4_11));
XOR2X1   g3497(.A(n9227), .B(CRC_OUT_4_10), .Y(n9228));
XOR2X1   g3496(.A(CRC_OUT_4_31), .B(WX7342), .Y(n9227));

endmodule
