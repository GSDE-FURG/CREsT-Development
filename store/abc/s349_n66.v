//Converted to Combinational (Partial output: n66) , Module name: s349_n66
module s349_n66 ( START, ACVQN1, CT1, CT2, CT0, MRVQN0, ACVQN0, ACVQN2, AX1, AX0, AX2, n66 );
input START, ACVQN1, CT1, CT2, CT0, MRVQN0, ACVQN0, ACVQN2, AX1, AX0, AX2;
output n66;
wire n83, n118, n89, n117, P5, READY, n88, n112, n113, n116, n115, n65, n67, n111_1, n108, n110, n96_1, n93, n95, n114, n109, n107, n94, n92, n90, n91_1, P0, P4;
NAND2X1  g53(.A(n118), .B(n83), .Y(n66));
INVX1    g18(.A(START), .Y(n83));
MX2X1    g52(.A(P5), .B(n117), .S0(n89), .Y(n118));
NOR2X1   g23(.A(n88), .B(READY), .Y(n89));
OAI22X1  g51(.A0(n115), .A1(n116), .B0(n113), .B1(n112), .Y(n117));
INVX1    g11(.A(ACVQN1), .Y(P5));
NOR3X1   g05(.A(n67), .B(CT1), .C(n65), .Y(READY));
NOR3X1   g22(.A(CT0), .B(CT1), .C(CT2), .Y(n88));
AOI21X1  g46(.A0(n110), .A1(n108), .B0(n111_1), .Y(n112));
INVX1    g47(.A(n96_1), .Y(n113));
NOR2X1   g50(.A(n95), .B(n93), .Y(n116));
OAI21X1  g49(.A0(n114), .A1(n112), .B0(n113), .Y(n115));
INVX1    g00(.A(CT2), .Y(n65));
INVX1    g02(.A(CT0), .Y(n67));
NOR3X1   g45(.A(n109), .B(MRVQN0), .C(ACVQN1), .Y(n111_1));
NOR3X1   g42(.A(n107), .B(MRVQN0), .C(ACVQN0), .Y(n108));
OAI21X1  g44(.A0(n109), .A1(MRVQN0), .B0(ACVQN1), .Y(n110));
NOR3X1   g30(.A(n94), .B(MRVQN0), .C(ACVQN2), .Y(n96_1));
OAI21X1  g27(.A0(n91_1), .A1(n90), .B0(n92), .Y(n93));
OAI21X1  g29(.A0(n94), .A1(MRVQN0), .B0(ACVQN2), .Y(n95));
INVX1    g48(.A(n95), .Y(n114));
INVX1    g43(.A(AX1), .Y(n109));
INVX1    g41(.A(AX0), .Y(n107));
INVX1    g28(.A(AX2), .Y(n94));
NAND3X1  g26(.A(AX1), .B(P0), .C(P5), .Y(n92));
NAND3X1  g24(.A(AX0), .B(P0), .C(P4), .Y(n90));
AOI21X1  g25(.A0(AX1), .A1(P0), .B0(P5), .Y(n91_1));
INVX1    g06(.A(MRVQN0), .Y(P0));
INVX1    g10(.A(ACVQN0), .Y(P4));

endmodule
