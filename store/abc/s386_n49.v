//Converted to Combinational (Partial output: n49) , Module name: s386_n49
module s386_n49 ( v0, v1, v10, v9, v12, v7, v11, v8, v3, v2, v5, v4, n49 );
input v0, v1, v10, v9, v12, v7, v11, v8, v3, v2, v5, v4;
output n49;
wire n81, n113, n116, n70, n71, n33, n108, n112, n34_1, n114, n115, n58, n37, n107, n111, n109, n110, n36;
AOI21X1  g85(.A0(n116), .A1(n113), .B0(n81), .Y(n49));
NAND3X1  g49(.A(n71), .B(v0), .C(n70), .Y(n81));
OAI21X1  g81(.A0(n112), .A1(n108), .B0(n33), .Y(n113));
OAI21X1  g84(.A0(n115), .A1(n114), .B0(n34_1), .Y(n116));
INVX1    g38(.A(v1), .Y(n70));
NOR2X1   g39(.A(v9), .B(v10), .Y(n71));
INVX1    g01(.A(v12), .Y(n33));
NOR3X1   g76(.A(n107), .B(n37), .C(n58), .Y(n108));
OAI21X1  g80(.A0(n110), .A1(n109), .B0(n111), .Y(n112));
INVX1    g02(.A(v7), .Y(n34_1));
NOR3X1   g82(.A(v8), .B(v11), .C(n33), .Y(n114));
NOR3X1   g83(.A(n37), .B(n36), .C(v12), .Y(n115));
INVX1    g26(.A(v3), .Y(n58));
INVX1    g05(.A(v8), .Y(n37));
MX2X1    g75(.A(v2), .B(v11), .S0(v7), .Y(n107));
NAND4X1  g79(.A(n37), .B(v11), .C(v5), .D(v7), .Y(n111));
AOI21X1  g77(.A0(n37), .A1(v2), .B0(v7), .Y(n109));
NAND2X1  g78(.A(n36), .B(v4), .Y(n110));
INVX1    g04(.A(v11), .Y(n36));

endmodule
