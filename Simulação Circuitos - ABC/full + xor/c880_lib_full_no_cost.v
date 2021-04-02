// Benchmark "c880.blif" written by ABC on Wed Mar 31 18:38:48 2021

module \c880.blif  ( 
    G1gat, G8gat, G13gat, G17gat, G26gat, G29gat, G36gat, G42gat, G51gat,
    G55gat, G59gat, G68gat, G72gat, G73gat, G74gat, G75gat, G80gat, G85gat,
    G86gat, G87gat, G88gat, G89gat, G90gat, G91gat, G96gat, G101gat,
    G106gat, G111gat, G116gat, G121gat, G126gat, G130gat, G135gat, G138gat,
    G143gat, G146gat, G149gat, G152gat, G153gat, G156gat, G159gat, G165gat,
    G171gat, G177gat, G183gat, G189gat, G195gat, G201gat, G207gat, G210gat,
    G219gat, G228gat, G237gat, G246gat, G255gat, G259gat, G260gat, G261gat,
    G267gat, G268gat,
    G388gat, G389gat, G390gat, G391gat, G418gat, G419gat, G420gat, G421gat,
    G422gat, G423gat, G446gat, G447gat, G448gat, G449gat, G450gat, G767gat,
    G768gat, G850gat, G863gat, G864gat, G865gat, G866gat, G874gat, G878gat,
    G879gat, G880gat  );
  input  G1gat, G8gat, G13gat, G17gat, G26gat, G29gat, G36gat, G42gat,
    G51gat, G55gat, G59gat, G68gat, G72gat, G73gat, G74gat, G75gat, G80gat,
    G85gat, G86gat, G87gat, G88gat, G89gat, G90gat, G91gat, G96gat,
    G101gat, G106gat, G111gat, G116gat, G121gat, G126gat, G130gat, G135gat,
    G138gat, G143gat, G146gat, G149gat, G152gat, G153gat, G156gat, G159gat,
    G165gat, G171gat, G177gat, G183gat, G189gat, G195gat, G201gat, G207gat,
    G210gat, G219gat, G228gat, G237gat, G246gat, G255gat, G259gat, G260gat,
    G261gat, G267gat, G268gat;
  output G388gat, G389gat, G390gat, G391gat, G418gat, G419gat, G420gat,
    G421gat, G422gat, G423gat, G446gat, G447gat, G448gat, G449gat, G450gat,
    G767gat, G768gat, G850gat, G863gat, G864gat, G865gat, G866gat, G874gat,
    G878gat, G879gat, G880gat;
  wire new_n87_, new_n88_, new_n89_, new_n91_, new_n92_, new_n95_, new_n96_,
    new_n98_, new_n99_, new_n100_, new_n101_, new_n103_, new_n104_,
    new_n109_, new_n110_, new_n111_, new_n114_, new_n116_, new_n117_,
    new_n118_, new_n119_, new_n121_, new_n123_, new_n125_, new_n126_,
    new_n127_, new_n128_, new_n129_, new_n130_, new_n131_, new_n132_,
    new_n134_, new_n135_, new_n136_, new_n137_, new_n138_, new_n139_,
    new_n140_, new_n141_, new_n143_, new_n144_, new_n145_, new_n146_,
    new_n147_, new_n148_, new_n149_, new_n150_, new_n151_, new_n152_,
    new_n153_, new_n154_, new_n155_, new_n156_, new_n157_, new_n158_,
    new_n159_, new_n160_, new_n161_, new_n162_, new_n163_, new_n164_,
    new_n165_, new_n166_, new_n167_, new_n168_, new_n169_, new_n170_,
    new_n171_, new_n172_, new_n173_, new_n175_, new_n176_, new_n177_,
    new_n178_, new_n179_, new_n180_, new_n181_, new_n182_, new_n183_,
    new_n184_, new_n185_, new_n186_, new_n187_, new_n188_, new_n189_,
    new_n190_, new_n191_, new_n192_, new_n193_, new_n194_, new_n195_,
    new_n196_, new_n197_, new_n198_, new_n199_, new_n200_, new_n201_,
    new_n202_, new_n203_, new_n204_, new_n206_, new_n207_, new_n208_,
    new_n209_, new_n210_, new_n211_, new_n212_, new_n213_, new_n214_,
    new_n215_, new_n216_, new_n217_, new_n218_, new_n219_, new_n220_,
    new_n222_, new_n223_, new_n224_, new_n225_, new_n226_, new_n227_,
    new_n228_, new_n229_, new_n230_, new_n231_, new_n233_, new_n234_,
    new_n235_, new_n236_, new_n237_, new_n238_, new_n239_, new_n240_,
    new_n241_, new_n242_, new_n243_, new_n244_, new_n245_, new_n246_,
    new_n247_, new_n248_, new_n249_, new_n250_, new_n251_, new_n252_,
    new_n253_, new_n254_, new_n255_, new_n256_, new_n257_, new_n258_,
    new_n259_, new_n260_, new_n262_, new_n263_, new_n264_, new_n265_,
    new_n266_, new_n267_, new_n268_, new_n269_, new_n270_, new_n272_,
    new_n273_, new_n274_, new_n275_, new_n276_, new_n277_, new_n278_,
    new_n279_, new_n280_, new_n282_, new_n283_, new_n284_, new_n285_,
    new_n286_, new_n287_, new_n288_, new_n289_, new_n290_, new_n291_,
    new_n292_, new_n293_, new_n294_, new_n296_, new_n297_, new_n298_,
    new_n299_, new_n300_, new_n301_, new_n302_, new_n303_, new_n304_,
    new_n305_;
  INV    g000(.A(G29gat), .Y(new_n87_));
  INV    g001(.A(G42gat), .Y(new_n88_));
  INV    g002(.A(G75gat), .Y(new_n89_));
  NOR3   g003(.A(new_n89_), .B(new_n88_), .C(new_n87_), .Y(G388gat));
  INV    g004(.A(G80gat), .Y(new_n91_));
  NAND2  g005(.A(G36gat), .B(G29gat), .Y(new_n92_));
  NOR2   g006(.A(new_n92_), .B(new_n91_), .Y(G389gat));
  NOR2   g007(.A(new_n92_), .B(new_n88_), .Y(G390gat));
  INV    g008(.A(G85gat), .Y(new_n95_));
  INV    g009(.A(G86gat), .Y(new_n96_));
  NOR2   g010(.A(new_n96_), .B(new_n95_), .Y(G391gat));
  INV    g011(.A(G1gat), .Y(new_n98_));
  INV    g012(.A(G8gat), .Y(new_n99_));
  INV    g013(.A(G13gat), .Y(new_n100_));
  INV    g014(.A(G17gat), .Y(new_n101_));
  NOR4   g015(.A(new_n101_), .B(new_n100_), .C(new_n99_), .D(new_n98_), .Y(G418gat));
  INV    g016(.A(G26gat), .Y(new_n103_));
  NOR4   g017(.A(new_n103_), .B(new_n101_), .C(new_n100_), .D(new_n98_), .Y(new_n104_));
  OAI21  g018(.A0(new_n92_), .A1(new_n88_), .B0(new_n104_), .Y(G419gat));
  NAND3  g019(.A(G80gat), .B(G75gat), .C(G59gat), .Y(G420gat));
  NAND3  g020(.A(G80gat), .B(G59gat), .C(G36gat), .Y(G421gat));
  NAND3  g021(.A(G59gat), .B(G42gat), .C(G36gat), .Y(G422gat));
  INV    g022(.A(G87gat), .Y(new_n109_));
  INV    g023(.A(G88gat), .Y(new_n110_));
  INV    g024(.A(G90gat), .Y(new_n111_));
  AOI21  g025(.A0(new_n110_), .A1(new_n109_), .B0(new_n111_), .Y(G423gat));
  NAND2  g026(.A(new_n104_), .B(G390gat), .Y(G446gat));
  INV    g027(.A(G51gat), .Y(new_n114_));
  NOR3   g028(.A(new_n114_), .B(new_n103_), .C(new_n98_), .Y(G447gat));
  INV    g029(.A(G55gat), .Y(new_n116_));
  NOR4   g030(.A(new_n116_), .B(new_n100_), .C(new_n99_), .D(new_n98_), .Y(new_n117_));
  INV    g031(.A(new_n117_), .Y(new_n118_));
  NAND2  g032(.A(G68gat), .B(G29gat), .Y(new_n119_));
  NOR2   g033(.A(new_n119_), .B(new_n118_), .Y(G448gat));
  NAND3  g034(.A(G74gat), .B(G68gat), .C(G59gat), .Y(new_n121_));
  NOR2   g035(.A(new_n121_), .B(new_n118_), .Y(G449gat));
  INV    g036(.A(G89gat), .Y(new_n123_));
  AOI21  g037(.A0(new_n110_), .A1(new_n109_), .B0(new_n123_), .Y(G450gat));
  XOR2   g038(.A(G96gat), .B(G91gat), .Y(new_n125_));
  XOR2   g039(.A(G106gat), .B(G101gat), .Y(new_n126_));
  XOR2   g040(.A(new_n126_), .B(new_n125_), .Y(new_n127_));
  XOR2   g041(.A(new_n127_), .B(G130gat), .Y(new_n128_));
  XOR2   g042(.A(G116gat), .B(G111gat), .Y(new_n129_));
  XOR2   g043(.A(G126gat), .B(G121gat), .Y(new_n130_));
  XOR2   g044(.A(new_n130_), .B(new_n129_), .Y(new_n131_));
  XOR2   g045(.A(new_n131_), .B(G135gat), .Y(new_n132_));
  XOR2   g046(.A(new_n132_), .B(new_n128_), .Y(G767gat));
  XOR2   g047(.A(G165gat), .B(G159gat), .Y(new_n134_));
  XOR2   g048(.A(G177gat), .B(G171gat), .Y(new_n135_));
  XOR2   g049(.A(new_n135_), .B(new_n134_), .Y(new_n136_));
  XOR2   g050(.A(new_n136_), .B(G130gat), .Y(new_n137_));
  XOR2   g051(.A(G189gat), .B(G183gat), .Y(new_n138_));
  XOR2   g052(.A(G201gat), .B(G195gat), .Y(new_n139_));
  XOR2   g053(.A(new_n139_), .B(new_n138_), .Y(new_n140_));
  XOR2   g054(.A(new_n140_), .B(G207gat), .Y(new_n141_));
  XOR2   g055(.A(new_n141_), .B(new_n137_), .Y(G768gat));
  INV    g056(.A(G261gat), .Y(new_n143_));
  INV    g057(.A(G126gat), .Y(new_n144_));
  INV    g058(.A(G153gat), .Y(new_n145_));
  OAI211 g059(.A0(G42gat), .A1(G17gat), .B0(G156gat), .B1(G59gat), .Y(new_n146_));
  AOI21  g060(.A0(G42gat), .A1(G17gat), .B0(new_n146_), .Y(new_n147_));
  NAND3  g061(.A(G75gat), .B(G59gat), .C(G42gat), .Y(new_n148_));
  NOR4   g062(.A(new_n114_), .B(new_n101_), .C(new_n99_), .D(new_n98_), .Y(new_n149_));
  AOI22  g063(.A0(new_n149_), .A1(new_n148_), .B0(new_n147_), .B1(G447gat), .Y(new_n150_));
  INV    g064(.A(G268gat), .Y(new_n151_));
  NOR3   g065(.A(new_n91_), .B(new_n89_), .C(new_n87_), .Y(new_n152_));
  NAND4  g066(.A(new_n152_), .B(G447gat), .C(new_n151_), .D(G55gat), .Y(new_n153_));
  NAND3  g067(.A(G51gat), .B(G26gat), .C(G1gat), .Y(new_n154_));
  AOI21  g068(.A0(G156gat), .A1(G59gat), .B0(new_n154_), .Y(new_n155_));
  AOI21  g069(.A0(new_n155_), .A1(G17gat), .B0(new_n98_), .Y(new_n156_));
  OAI221 g070(.A0(new_n156_), .A1(new_n145_), .C0(new_n153_), .B0(new_n150_), .B1(new_n144_), .Y(new_n157_));
  XOR2   g071(.A(new_n157_), .B(G201gat), .Y(new_n158_));
  INV    g072(.A(new_n158_), .Y(new_n159_));
  NOR2   g073(.A(new_n159_), .B(new_n143_), .Y(new_n160_));
  OAI21  g074(.A0(new_n158_), .A1(G261gat), .B0(G219gat), .Y(new_n161_));
  INV    g075(.A(G237gat), .Y(new_n162_));
  NAND2  g076(.A(new_n157_), .B(G201gat), .Y(new_n163_));
  NAND2  g077(.A(new_n157_), .B(G246gat), .Y(new_n164_));
  NAND2  g078(.A(G59gat), .B(G42gat), .Y(new_n165_));
  NAND3  g079(.A(G73gat), .B(G72gat), .C(G68gat), .Y(new_n166_));
  NOR3   g080(.A(new_n166_), .B(new_n165_), .C(new_n118_), .Y(new_n167_));
  INV    g081(.A(G121gat), .Y(new_n168_));
  INV    g082(.A(G210gat), .Y(new_n169_));
  NOR2   g083(.A(new_n169_), .B(new_n168_), .Y(new_n170_));
  AOI221 g084(.A0(new_n167_), .A1(G201gat), .C0(new_n170_), .B0(G267gat), .B1(G255gat), .Y(new_n171_));
  OAI211 g085(.A0(new_n163_), .A1(new_n162_), .B0(new_n171_), .B1(new_n164_), .Y(new_n172_));
  AOI21  g086(.A0(new_n158_), .A1(G228gat), .B0(new_n172_), .Y(new_n173_));
  OAI21  g087(.A0(new_n161_), .A1(new_n160_), .B0(new_n173_), .Y(G850gat));
  INV    g088(.A(G111gat), .Y(new_n175_));
  INV    g089(.A(G143gat), .Y(new_n176_));
  OAI221 g090(.A0(new_n156_), .A1(new_n176_), .C0(new_n153_), .B0(new_n150_), .B1(new_n175_), .Y(new_n177_));
  XOR2   g091(.A(new_n177_), .B(G183gat), .Y(new_n178_));
  INV    g092(.A(G116gat), .Y(new_n179_));
  INV    g093(.A(G146gat), .Y(new_n180_));
  OAI221 g094(.A0(new_n156_), .A1(new_n180_), .C0(new_n153_), .B0(new_n150_), .B1(new_n179_), .Y(new_n181_));
  INV    g095(.A(G149gat), .Y(new_n182_));
  OAI221 g096(.A0(new_n156_), .A1(new_n182_), .C0(new_n153_), .B0(new_n150_), .B1(new_n168_), .Y(new_n183_));
  OAI22  g097(.A0(new_n183_), .A1(G195gat), .B0(new_n181_), .B1(G189gat), .Y(new_n184_));
  OAI21  g098(.A0(new_n157_), .A1(G201gat), .B0(G261gat), .Y(new_n185_));
  NOR2   g099(.A(new_n185_), .B(new_n184_), .Y(new_n186_));
  NAND2  g100(.A(new_n181_), .B(G189gat), .Y(new_n187_));
  OAI211 g101(.A0(new_n181_), .A1(G189gat), .B0(new_n183_), .B1(G195gat), .Y(new_n188_));
  OAI211 g102(.A0(new_n184_), .A1(new_n163_), .B0(new_n188_), .B1(new_n187_), .Y(new_n189_));
  OAI21  g103(.A0(new_n189_), .A1(new_n186_), .B0(new_n178_), .Y(new_n190_));
  INV    g104(.A(new_n178_), .Y(new_n191_));
  NOR2   g105(.A(new_n189_), .B(new_n186_), .Y(new_n192_));
  NAND2  g106(.A(new_n192_), .B(new_n191_), .Y(new_n193_));
  NAND3  g107(.A(new_n193_), .B(new_n190_), .C(G219gat), .Y(new_n194_));
  INV    g108(.A(G246gat), .Y(new_n195_));
  NAND2  g109(.A(new_n147_), .B(G447gat), .Y(new_n196_));
  NAND2  g110(.A(new_n149_), .B(new_n148_), .Y(new_n197_));
  NAND2  g111(.A(new_n197_), .B(new_n196_), .Y(new_n198_));
  OAI21  g112(.A0(new_n156_), .A1(new_n176_), .B0(new_n153_), .Y(new_n199_));
  AOI21  g113(.A0(new_n198_), .A1(G111gat), .B0(new_n199_), .Y(new_n200_));
  NAND2  g114(.A(new_n177_), .B(G183gat), .Y(new_n201_));
  AOI22  g115(.A0(new_n167_), .A1(G183gat), .B0(G210gat), .B1(G106gat), .Y(new_n202_));
  OAI221 g116(.A0(new_n201_), .A1(new_n162_), .C0(new_n202_), .B0(new_n200_), .B1(new_n195_), .Y(new_n203_));
  AOI21  g117(.A0(new_n178_), .A1(G228gat), .B0(new_n203_), .Y(new_n204_));
  NAND2  g118(.A(new_n204_), .B(new_n194_), .Y(G863gat));
  XOR2   g119(.A(new_n181_), .B(G189gat), .Y(new_n206_));
  INV    g120(.A(new_n206_), .Y(new_n207_));
  NOR2   g121(.A(new_n183_), .B(G195gat), .Y(new_n208_));
  NOR2   g122(.A(new_n185_), .B(new_n208_), .Y(new_n209_));
  NAND2  g123(.A(new_n183_), .B(G195gat), .Y(new_n210_));
  OAI21  g124(.A0(new_n208_), .A1(new_n163_), .B0(new_n210_), .Y(new_n211_));
  NOR2   g125(.A(new_n211_), .B(new_n209_), .Y(new_n212_));
  INV    g126(.A(G219gat), .Y(new_n213_));
  AOI21  g127(.A0(new_n212_), .A1(new_n207_), .B0(new_n213_), .Y(new_n214_));
  OAI21  g128(.A0(new_n212_), .A1(new_n207_), .B0(new_n214_), .Y(new_n215_));
  NAND2  g129(.A(G259gat), .B(G255gat), .Y(new_n216_));
  OAI21  g130(.A0(new_n169_), .A1(new_n175_), .B0(new_n216_), .Y(new_n217_));
  AOI221 g131(.A0(new_n181_), .A1(G246gat), .C0(new_n217_), .B0(new_n167_), .B1(G189gat), .Y(new_n218_));
  OAI21  g132(.A0(new_n187_), .A1(new_n162_), .B0(new_n218_), .Y(new_n219_));
  AOI21  g133(.A0(new_n206_), .A1(G228gat), .B0(new_n219_), .Y(new_n220_));
  NAND2  g134(.A(new_n220_), .B(new_n215_), .Y(G864gat));
  XOR2   g135(.A(new_n183_), .B(G195gat), .Y(new_n222_));
  INV    g136(.A(new_n222_), .Y(new_n223_));
  AOI21  g137(.A0(new_n185_), .A1(new_n163_), .B0(new_n223_), .Y(new_n224_));
  NAND2  g138(.A(new_n185_), .B(new_n163_), .Y(new_n225_));
  OAI21  g139(.A0(new_n225_), .A1(new_n222_), .B0(G219gat), .Y(new_n226_));
  NAND2  g140(.A(G260gat), .B(G255gat), .Y(new_n227_));
  OAI21  g141(.A0(new_n169_), .A1(new_n179_), .B0(new_n227_), .Y(new_n228_));
  AOI221 g142(.A0(new_n183_), .A1(G246gat), .C0(new_n228_), .B0(new_n167_), .B1(G195gat), .Y(new_n229_));
  OAI21  g143(.A0(new_n210_), .A1(new_n162_), .B0(new_n229_), .Y(new_n230_));
  AOI21  g144(.A0(new_n222_), .A1(G228gat), .B0(new_n230_), .Y(new_n231_));
  OAI21  g145(.A0(new_n226_), .A1(new_n224_), .B0(new_n231_), .Y(G865gat));
  INV    g146(.A(G91gat), .Y(new_n233_));
  AOI211 g147(.A0(G156gat), .A1(G59gat), .B(new_n154_), .C(new_n116_), .Y(new_n234_));
  NAND3  g148(.A(G80gat), .B(G75gat), .C(G29gat), .Y(new_n235_));
  NOR4   g149(.A(new_n235_), .B(new_n154_), .C(G268gat), .D(new_n101_), .Y(new_n236_));
  AOI221 g150(.A0(new_n234_), .A1(G143gat), .C0(new_n236_), .B0(G138gat), .B1(G8gat), .Y(new_n237_));
  OAI21  g151(.A0(new_n150_), .A1(new_n233_), .B0(new_n237_), .Y(new_n238_));
  NAND2  g152(.A(new_n238_), .B(G159gat), .Y(new_n239_));
  INV    g153(.A(G96gat), .Y(new_n240_));
  AOI221 g154(.A0(new_n234_), .A1(G146gat), .C0(new_n236_), .B0(G138gat), .B1(G51gat), .Y(new_n241_));
  OAI21  g155(.A0(new_n150_), .A1(new_n240_), .B0(new_n241_), .Y(new_n242_));
  INV    g156(.A(G101gat), .Y(new_n243_));
  INV    g157(.A(G171gat), .Y(new_n244_));
  AOI221 g158(.A0(new_n234_), .A1(G149gat), .C0(new_n236_), .B0(G138gat), .B1(G17gat), .Y(new_n245_));
  OAI211 g159(.A0(new_n150_), .A1(new_n243_), .B0(new_n245_), .B1(new_n244_), .Y(new_n246_));
  OAI21  g160(.A0(new_n242_), .A1(G165gat), .B0(new_n246_), .Y(new_n247_));
  INV    g161(.A(G106gat), .Y(new_n248_));
  INV    g162(.A(new_n234_), .Y(new_n249_));
  AOI21  g163(.A0(G152gat), .A1(G138gat), .B0(new_n236_), .Y(new_n250_));
  OAI221 g164(.A0(new_n249_), .A1(new_n145_), .C0(new_n250_), .B0(new_n150_), .B1(new_n248_), .Y(new_n251_));
  NOR2   g165(.A(new_n251_), .B(G177gat), .Y(new_n252_));
  OAI22  g166(.A0(new_n189_), .A1(new_n186_), .B0(new_n177_), .B1(G183gat), .Y(new_n253_));
  AOI211 g167(.A0(new_n253_), .A1(new_n201_), .B(new_n252_), .C(new_n247_), .Y(new_n254_));
  NAND2  g168(.A(new_n251_), .B(G177gat), .Y(new_n255_));
  NAND2  g169(.A(new_n242_), .B(G165gat), .Y(new_n256_));
  OAI21  g170(.A0(new_n150_), .A1(new_n243_), .B0(new_n245_), .Y(new_n257_));
  OAI211 g171(.A0(new_n242_), .A1(G165gat), .B0(new_n257_), .B1(G171gat), .Y(new_n258_));
  OAI211 g172(.A0(new_n255_), .A1(new_n247_), .B0(new_n258_), .B1(new_n256_), .Y(new_n259_));
  OAI22  g173(.A0(new_n259_), .A1(new_n254_), .B0(new_n238_), .B1(G159gat), .Y(new_n260_));
  NAND2  g174(.A(new_n260_), .B(new_n239_), .Y(G866gat));
  XOR2   g175(.A(new_n251_), .B(G177gat), .Y(new_n262_));
  INV    g176(.A(new_n262_), .Y(new_n263_));
  AOI21  g177(.A0(new_n253_), .A1(new_n201_), .B0(new_n263_), .Y(new_n264_));
  NAND2  g178(.A(new_n253_), .B(new_n201_), .Y(new_n265_));
  OAI21  g179(.A0(new_n262_), .A1(new_n265_), .B0(G219gat), .Y(new_n266_));
  NOR2   g180(.A(new_n169_), .B(new_n243_), .Y(new_n267_));
  AOI221 g181(.A0(new_n251_), .A1(G246gat), .C0(new_n267_), .B0(new_n167_), .B1(G177gat), .Y(new_n268_));
  OAI21  g182(.A0(new_n255_), .A1(new_n162_), .B0(new_n268_), .Y(new_n269_));
  AOI21  g183(.A0(new_n262_), .A1(G228gat), .B0(new_n269_), .Y(new_n270_));
  OAI21  g184(.A0(new_n266_), .A1(new_n264_), .B0(new_n270_), .Y(G874gat));
  NOR2   g185(.A(new_n259_), .B(new_n254_), .Y(new_n272_));
  XOR2   g186(.A(new_n238_), .B(G159gat), .Y(new_n273_));
  INV    g187(.A(new_n273_), .Y(new_n274_));
  AOI21  g188(.A0(new_n274_), .A1(new_n272_), .B0(new_n213_), .Y(new_n275_));
  OAI21  g189(.A0(new_n274_), .A1(new_n272_), .B0(new_n275_), .Y(new_n276_));
  NOR2   g190(.A(new_n151_), .B(new_n169_), .Y(new_n277_));
  AOI221 g191(.A0(new_n238_), .A1(G246gat), .C0(new_n277_), .B0(new_n167_), .B1(G159gat), .Y(new_n278_));
  OAI21  g192(.A0(new_n239_), .A1(new_n162_), .B0(new_n278_), .Y(new_n279_));
  AOI21  g193(.A0(new_n273_), .A1(G228gat), .B0(new_n279_), .Y(new_n280_));
  NAND2  g194(.A(new_n280_), .B(new_n276_), .Y(G878gat));
  XOR2   g195(.A(new_n242_), .B(G165gat), .Y(new_n282_));
  INV    g196(.A(new_n282_), .Y(new_n283_));
  INV    g197(.A(new_n246_), .Y(new_n284_));
  AOI211 g198(.A0(new_n253_), .A1(new_n201_), .B(new_n252_), .C(new_n284_), .Y(new_n285_));
  NAND2  g199(.A(new_n257_), .B(G171gat), .Y(new_n286_));
  OAI21  g200(.A0(new_n255_), .A1(new_n284_), .B0(new_n286_), .Y(new_n287_));
  NOR2   g201(.A(new_n287_), .B(new_n285_), .Y(new_n288_));
  AOI21  g202(.A0(new_n288_), .A1(new_n283_), .B0(new_n213_), .Y(new_n289_));
  OAI21  g203(.A0(new_n288_), .A1(new_n283_), .B0(new_n289_), .Y(new_n290_));
  NOR2   g204(.A(new_n169_), .B(new_n233_), .Y(new_n291_));
  AOI221 g205(.A0(new_n242_), .A1(G246gat), .C0(new_n291_), .B0(new_n167_), .B1(G165gat), .Y(new_n292_));
  OAI21  g206(.A0(new_n256_), .A1(new_n162_), .B0(new_n292_), .Y(new_n293_));
  AOI21  g207(.A0(new_n282_), .A1(G228gat), .B0(new_n293_), .Y(new_n294_));
  NAND2  g208(.A(new_n294_), .B(new_n290_), .Y(G879gat));
  OAI21  g209(.A0(new_n251_), .A1(G177gat), .B0(new_n265_), .Y(new_n296_));
  XOR2   g210(.A(new_n257_), .B(G171gat), .Y(new_n297_));
  INV    g211(.A(new_n297_), .Y(new_n298_));
  AOI21  g212(.A0(new_n255_), .A1(new_n296_), .B0(new_n298_), .Y(new_n299_));
  NAND3  g213(.A(new_n298_), .B(new_n255_), .C(new_n296_), .Y(new_n300_));
  NAND2  g214(.A(new_n300_), .B(G219gat), .Y(new_n301_));
  NOR2   g215(.A(new_n169_), .B(new_n240_), .Y(new_n302_));
  AOI221 g216(.A0(new_n257_), .A1(G246gat), .C0(new_n302_), .B0(new_n167_), .B1(G171gat), .Y(new_n303_));
  OAI21  g217(.A0(new_n286_), .A1(new_n162_), .B0(new_n303_), .Y(new_n304_));
  AOI21  g218(.A0(new_n297_), .A1(G228gat), .B0(new_n304_), .Y(new_n305_));
  OAI21  g219(.A0(new_n301_), .A1(new_n299_), .B0(new_n305_), .Y(G880gat));
endmodule


