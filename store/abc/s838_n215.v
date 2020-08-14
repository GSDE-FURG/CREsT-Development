//Converted to Combinational (Partial output: n215) , Module name: s838_n215
module s838_n215 ( Y_31, Y_30, Y_32, Clear, Y_29, Y_27, Y_26, Y_25, Y_28, n215 );
input Y_31, Y_30, Y_32, Clear, Y_29, Y_27, Y_26, Y_25, Y_28;
output n215;
wire n316, n318, n315, n133, n134, n317, n306, n136, n137;
NAND2X1  g186(.A(n318), .B(n316), .Y(n215));
NAND4X1  g183(.A(Y_30), .B(Y_31), .C(n133), .D(n315), .Y(n316));
NAND3X1  g185(.A(n317), .B(n134), .C(Y_32), .Y(n318));
NOR4X1   g182(.A(n137), .B(n136), .C(Clear), .D(n306), .Y(n315));
INVX1    g000(.A(Y_32), .Y(n133));
NAND3X1  g001(.A(Y_29), .B(Y_30), .C(Y_31), .Y(n134));
NOR3X1   g184(.A(n306), .B(n136), .C(Clear), .Y(n317));
NAND3X1  g173(.A(Y_25), .B(Y_26), .C(Y_27), .Y(n306));
INVX1    g003(.A(Y_28), .Y(n136));
INVX1    g004(.A(Y_29), .Y(n137));

endmodule
