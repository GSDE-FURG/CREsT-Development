// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module C17.blif ( 
    \1GAT(0) , \2GAT(1) , \3GAT(2) , \6GAT(3) , \7GAT(4) ,
    \22GAT(10) , \23GAT(9)   );
  input  \1GAT(0) , \2GAT(1) , \3GAT(2) , \6GAT(3) , \7GAT(4) ;
  output \22GAT(10) , \23GAT(9) ;
  wire n7, n8, n9, n11;
  NAND2X1 g0(.A(\3GAT(2) ), .B(\1GAT(0) ), .Y(n7));
  NAND2X1 g1(.A(\6GAT(3) ), .B(\3GAT(2) ), .Y(n8));
  NAND2X1 g2(.A(n8), .B(\2GAT(1) ), .Y(n9));
  NAND2X1 g3(.A(n9), .B(n7), .Y(\22GAT(10) ));
  NAND2X1 g4(.A(n8), .B(\7GAT(4) ), .Y(n11));
  NAND2X1 g5(.A(n11), .B(n9), .Y(\23GAT(9) ));
endmodule


