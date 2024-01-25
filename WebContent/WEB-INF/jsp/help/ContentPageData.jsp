<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Vital eHealth Network User's Manual </title>
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;}
@font-face
	{font-family:"Arial Black";
	panose-1:2 11 10 4 2 1 2 2 2 4;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{
	margin:0in;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";
}
h1
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:.5in;
	text-indent:-.5in;
	page-break-after:avoid;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:14.0pt;
	font-family:Arial;
	text-transform:uppercase;}
h2
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	page-break-after:avoid;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:14.0pt;
	font-family:Arial;}
h3
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	page-break-after:avoid;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:12.0pt;
	font-family:Arial;}
h4
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	page-break-after:avoid;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:Arial;
	font-weight:normal;
	font-style:italic;}
h5
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:Arial;}
h6
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";
	font-weight:normal;
	font-style:italic;}
p.MsoHeading7, li.MsoHeading7, div.MsoHeading7
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:Arial;}
p.MsoHeading8, li.MsoHeading8, div.MsoHeading8
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:Arial;
	font-style:italic;}
p.MsoHeading9, li.MsoHeading9, div.MsoHeading9
	{margin-top:12.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:0in;
	text-indent:0in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:9.0pt;
	font-family:Arial;
	font-weight:bold;
	font-style:italic;}
p.MsoToc1, li.MsoToc1, div.MsoToc1
	{margin-top:6.0pt;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:0in;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";
	font-weight:bold;
	font-style:italic;}
p.MsoToc2, li.MsoToc2, div.MsoToc2
	{margin-top:6.0pt;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:12.0pt;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";
	font-weight:bold;}
p.MsoToc3, li.MsoToc3, div.MsoToc3
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:24.0pt;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoToc4, li.MsoToc4, div.MsoToc4
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:.5in;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoToc5, li.MsoToc5, div.MsoToc5
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:48.0pt;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoToc6, li.MsoToc6, div.MsoToc6
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:60.0pt;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoToc7, li.MsoToc7, div.MsoToc7
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:1.0in;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoToc8, li.MsoToc8, div.MsoToc8
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:84.0pt;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoToc9, li.MsoToc9, div.MsoToc9
	{margin-top:0in;
	margin-right:0in;
	margin-bottom:0in;
	margin-left:96.0pt;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:10.0pt;
	font-family:"Times New Roman";}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{margin:0in;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{margin:0in;
	margin-bottom:.0001pt;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";}
a:link, span.MsoHyperlink
	{color:blue;
	text-decoration:underline;}
a:visited, span.MsoHyperlinkFollowed
	{color:purple;
	text-decoration:underline;}
p.bullet2, li.bullet2, div.bullet2
	{margin-top:3.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:1.0in;
	text-indent:-.5in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";}
p.bullet, li.bullet, div.bullet
	{margin-top:3.0pt;
	margin-right:0in;
	margin-bottom:3.0pt;
	margin-left:.5in;
	text-indent:-.5in;
	punctuation-wrap:simple;
	text-autospace:none;
	font-size:11.0pt;
	font-family:"Times New Roman";}
span.Heading4Char
	{font-family:Arial;
	font-style:italic;}
 /* Page Definitions */
@page Section1
	{size:8.5in 11.0in;
	margin:1.0in 1.0in 1.0in 1.0in;}
div.Section1
	{
	page:Section1;
	text-align: right;
}
@page Section2
	{size:8.5in 11.0in;
	margin:1.0in 1.0in 1.0in 1.0in;}
div.Section2
	{page:Section2;}
@page Section3
	{size:8.5in 11.0in;
	margin:63.0pt 1.0in 67.5pt 1.0in;}
div.Section3
	{page:Section3;}
 /* List Definitions */
 ol
	{margin-bottom:0in;}
ul
	{margin-bottom:0in;}
-->
</style>
<link href="images/fpstylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body lang=EN-US link=blue vlink=purple  leftmargin="5" topmargin="5">

<div class=Section1>

<p><img src="images/helpimages/image001.jpg" width=151 height=145 border="0">
  <br />
  <br />
  </p>
<p><br />
  
</p>
<h1>User Manual</h1>
<p><br />
  <img width=590 height=161 src="images/helpimages/image002.jpg" /><br />
  <br />
  <br />
  </p>
<p>&nbsp;</p>
<p><span style='font-size:14.0pt'>Version 1.0</span></p>
</div>

<span style='font-size:11.0pt;font-family:"Times New Roman"'><br clear=all
style='page-break-before:always'>
</span>

<div class=Section2>



</div>

<span style='font-size:11.0pt;font-family:"Times New Roman"'><br clear=all
style='page-break-before:always'>
</span>

<div class=Section3>

  <p><b><span style='font-size:22.0pt;font-family:Arial'><a name="chapter1"></a><img width=125 height=125 src="images/helpimages/image003.gif"
alt="Rounded Rectangle: 1&#13;&#10;CHAPTER&#13;&#10;"></span></b><b><span style='font-size:22.0pt;font-family:Arial'>  GENERAL INFORMATION</span></b></p>
  <p class=MsoNormal>&nbsp;</p>

<h2 style='margin-left:0in;text-indent:0in'>
<a name="chapter11"></a>1.1  Introduction</h2>

<p class=bullet style='text-align:justify;text-indent:0in'>Vital eHealth
network is a software framework for physicians, specialist doctors and
laboratories. Using this network, physicians can order clinical tests of their
patients to laboratories and get the reports back over the Internet. Physicians
can send patient records to specialists and get 2<sup>nd</sup> opinion online.</p>

<h2><a name="chapter12"></a>1.2    System Overview</h2>

<p class=bullet style='text-align:justify;text-indent:0in'>The software
framework works over the Internet. Users get connected to the hosted server
using broadband or any wireless Internet connectivity. Each user subscribes to
the service and gets a secured username and password. </p>

<p class=bullet style='margin-left:0in;text-indent:.5in'>There are 4 entities
in this network. Each of them is explained below.</p>

<p class=bullet style='margin-left:.75in;text-align:justify;text-indent:-.25in'><span
style='font-family:Symbol'>·<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span><b>TeleVital</b>: The software service provider. TeleVital hosts
the software over the Internet and acts as an administrator. </p>

<p class=bullet style='margin-left:.75in;text-align:justify;text-indent:-.25in'><span
style='font-family:Symbol'>·<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span><b>Physicians</b>: Physicians are users who use the services of
other entities like Laboratories and Specialists. </p>

<p class=bullet style='margin-left:.75in;text-align:justify;text-indent:-.25in'><span
style='font-family:Symbol'>·<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span><b>Specialists</b>: These are specialist doctors who receive
patient records from physicians and provide 2<sup>nd</sup> opinion.</p>

<p class=bullet style='margin-left:.75in;text-align:justify;text-indent:-.25in'><span
style='font-family:Symbol'>·<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span><b>Laboratories</b>: The clinical laboratories who receive
patients’ test order from physicians. They do the patient test and upload the
reports to the corresponding patient record.</p>

<p class=bullet style='margin-left:0in;text-indent:0in'><b>&nbsp;</b></p>

<h2 style='margin-left:0in;text-indent:0in'><a name="chapter13"></a>1.3    Contact Information</h2>

<p class=bullet style='margin-left:0in;text-indent:0in'>            <b>Company
Address: </b></p>

<p class=bullet style='text-indent:0in'><b><span style='font-size:12.0pt'>TeleVital
India Pvt. Ltd.</span></b><br>
2<sup>nd</sup> Floor, PESIT Tech Park, 
100 Feet Ring Road, Banashankari 3<sup>rd</sup> Stage, 
Bangalore -560085</p>

<p class=bullet style='margin-left:0in;text-indent:0in'>            <b>Phone:</b>
+91 80 264 20001 <b>Fax:</b> +91 80 2642 0304</p>

<p class=bullet style='text-indent:0in'><b>Website:</b> <a
href="http://www.televital.com/">www.televital.com</a>  <b>Email: </b><a href="mailto:Support@televital.com">Support@televital.com</a></p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<p class=MsoNormal><span style="text-align: left"><b></b></span><b><span style='font-size:22.0pt;font-family:Arial'><a name="chapter2"></a><img
width=125 height=125 src="images/helpimages/image004.gif"
alt="Rounded Rectangle: 2&#13;&#10;CHAPTER&#13;&#10;"></span></b><b><span style='font-size:22.0pt;font-family:Arial'> SYSTEM SUMMARY</span></b></p>

<b><span style='font-size:14.0pt;font-family:Arial;text-transform:uppercase'><br
clear=all style='page-break-before:always'>
</span></b>

<h1><a name="chapter21"></a>2.1<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span>System Workflow</h1>
<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal><img width=580 height=536
src="images/helpimages/image005.gif"></p>

<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal>&nbsp;</p>
<p class=MsoNormal><b><span style='font-size:14.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal><b><span style='font-size:14.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal><b><span style='font-size:14.0pt'><img width=636 height=425
src="images/helpimages/image006.gif"></span></b></p>

<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal>&nbsp;</p>

<h2 style='margin-left:0in;text-indent:0in'><a name="chapter22"></a>2.2    Hardware
Requirements</h2>

<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal style='margin-left:.75in;text-indent:-.25in;line-height:
150%'>1.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span>Laptop / PC: Recommended: Core 2 Duo, 2 GB Ram, 320 GB HDD or any higher
configuration.</p>

<p class=MsoNormal style='margin-left:.75in;text-indent:-.25in;line-height:
150%'>2.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span>Webcam: Recommended: 3 Mega pixel or above.</p>

<p class=MsoNormal style='margin-left:.75in;text-indent:-.25in;line-height:
150%'>3.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span>Broadband Internet Connection: Recommended 256 Kbps or above.</p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<p class=MsoNormal><b><span style='font-size:22.0pt;font-family:Arial'><a name="chapter3"></a><img
width=125 height=125 src="images/helpimages/image007.gif"
alt="Rounded Rectangle: 3&#13;&#10;CHAPTER&#13;&#10;"></span></b> <b><span style='font-size:22.0pt;font-family:Arial'>GETTING STARTED</span></b></p>

<b><span style='font-size:14.0pt;font-family:Arial;text-transform:uppercase'><br
clear=all style='page-break-before:always'>
</span></b>

<h1><a name="chapter31"></a>3.1    Logging
In</h1>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Ensure that you
  are connected to the Internet. Using a standard browser go to home page of      Vital
  eHealth and provide username and password. On successful validation you will be
  taken to the homepage and the menus displayed will be as per your role.</p>

<p class=MsoNormal style='text-indent:.5in'><span style='font-size:7.0pt'>&nbsp;</span></p>

<p class=MsoNormal align=center style='text-align:center'><img border=0
width=410 height=308 src="images/helpimages/image008.jpg"></p>

<h2 style='margin-left:0in;text-indent:0in'><a name="chapter32"></a>3.2    Logging
Out</h2>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>You can exit the
system by clicking on the “Logout” on the top right section. Once you logout
you will not be able to use the Account features. It is a good practice to
logout after completing your work in site so that others don’t misuse your
account.</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'><span
style='font-size:8.0pt'>&nbsp;</span></p>

<p class=MsoNormal align=center style='text-align:center'><img border=0
width=410 height=308 src="images/helpimages/image009.jpg"></p>

<h2 style='margin-left:0in;text-indent:0in'><a name="chapter33">3.3    Change
Profile and password.</a></h2>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>You can change
your password in the setting section. Provide old password and new password in
the space provided. </p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>You can update
in the setting section any changes in your profile like address, phone and
email details. </p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'><img width=579
height=753 src="images/helpimages/image010.gif"></p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<p class=MsoNormal><b><span style='font-size:22.0pt;font-family:Arial'><a name="chapter4"></a><img
width=125 height=125 src="images/helpimages/image011.gif"
alt="Rounded Rectangle: 4&#13;&#10;CHAPTER&#13;&#10;"></span><span style="font-family: Arial; font-size: 22.0pt"> </span><span style='font-size:22.0pt;font-family:Arial'>USING THE SYSTEM</span></b></p>
<p class=MsoNormal>&nbsp;</p>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td width="7%">&nbsp;</td>
    <td><p><a href="#chapter41"><strong>4.1 Family Physician</strong></a><br />
  &nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter411">4.1.1 Creating network of Specialists and Labs</a><br />
  &nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter412">4.1.2 Patient Management</a><br />
  &nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter413">4.1.3 Electronic Medical Record (EMR)</a></p>
      <p><span class="MsoNormal"><a href="#chapter42"><strong>4.2 Laboratory Account</strong></a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter421">4.2.1 Patient List</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter422">4.2.2 Working on tests and reports</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter423">4.2.3 Lab services</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter424">4.2.4 My Network</a></span></p>
      <p><a href="#chapter43"><strong>4.3 Specialist Role</strong></a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter431">4.3.1 Patient List</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter432">4.3.2 EMR view of the specialist</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter433">4.3.3 My Network</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter434">4.3.4 Order Lab services</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#chapter435">4.3.5 Patient creation and referral</a></p></td>
  </tr>
</table>
<p class=MsoNormal>&nbsp;</p>
<p class=MsoNormal>&nbsp;</p>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'>There are 3
  roles in the system, Family Physician, Specialist Doctor and Laboratory. This
  chapter explains how to use the system for each of the roles. </p>

<h2 style='margin-left:0in;text-indent:0in'><a name="chapter41"></a>4.1 Family
Physician</h2>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td width="7%">&nbsp;</td>
    <td><a href="#chapter411">4.1.1 Creating network of Specialists and Labs</a><br />      <a href="#chapter412">4.1.2 Patient Management</a><br />      <a href="#chapter413">4.1.3 Electronic Medical Record (EMR)</a></td>
  </tr>
</table>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'><br />
  A family
Physician can create patients and fill up the Electronic Medical Record. A
patients EMR can be shared with a Specialist in the network and get second
opinion. Incase a patient requires a medical test; the physician can order a
test online to the nearest lab available on the network. The various tasks are
explained in more detail below.</p>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter411">4.1.1 Creating
network of Specialists and LABS</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>A family
physician should create his own network of specialists and LABS. This kind of
building a network ensures that the physician is working with specialists and
labs with whom he is comfortable. The steps to build a network of Specialists
and Labs are shown in the diagram below</p>

<p class=MsoNormal>&nbsp;</p>
<p class=MsoNormal>
  <img width=557 height=400 src="images/helpimages/image012.gif"></p>

<p class=MsoNormal align=right style='text-align:right'>&nbsp;</p>

<span style='font-size:11.0pt;font-family:"Times New Roman"'><br clear=all
style='page-break-before:always'>
</span>

<p class=MsoNormal><img width=627 height=870
src="images/helpimages/image014.gif"><br clear=all style='page-break-before:
always'>
<img width=627 height=875 src="images/helpimages/image015.gif"></p>

<b><span style='font-size:12.0pt;font-family:Arial'><br clear=all
style='page-break-before:always'>
</span></b>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter412">4.1.2 Patient
Management</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Once you login,
you will be taken to patient Management page by default. The page displays the
list of patients you are already working with. In this page you can create,
edit and delete patients.</p>

<p class=MsoNormal align=center style='text-align:center'>&nbsp;</p>

<p class=MsoNormal align=center style='text-align:center'><img border=0
width=362 height=272 src="images/helpimages/image016.jpg"></p>

<h4 style='margin-left:0in;text-indent:0in'><a name="chapter412a">Creating
new patients.</a></h4>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>To create new
patient click on the link <u>new/edit patient</u> provided in the page. Fill-up
the patient registration form and submit to save patient details. The image
below shows the steps to create a new patient.</p>

<p class=MsoNormal align=center style='text-align:center'><img width=625
height=415 src="images/helpimages/image017.gif"></p>

<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal><a name="chapter412b"><span class=Heading4Char>Edit/Delete
patient</span></a>. </p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>In the page
where patient list is displayed, there are links to edit and delete patients on
respective rows. When you click on edit, a form is displayed with patient’s
details. Change the required fields and save the form. When you click on delete
the patient is deleted if there are no open cases pending. The image below
shows the same. </p>

<p class=MsoNormal style='text-align:justify'><img width=627 height=761
src="images/helpimages/image018.gif"></p>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter413">4.1.3 Electronic
Medical Record (EMR)</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>The EMR is
comprehensive and easy to use. It contains Patient’s demographics, complaint
and symptoms, history, observations, Vital Signs, lab orders, 2<sup>nd</sup> opinions,
diagnosis.</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>General features
of the EMR</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>EMR is a
combination of many cases of the patient. At any time, the latest case is open
and all previous cases are closed. The facility to start and close a case lies
with the Family Physician. </p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Starting a case:
Choose the patient from the list and click on new case. The diagram below show
the same.</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Filling up the
EMR.: In the EMR, Click “edit all” and EMR form comes up.</p>

<p class=MsoNormal>&nbsp;</p>

<div align=center>

<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
 style='border-collapse:collapse;border:none'>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Patient Header</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border:solid windowtext 1.0pt;
  border-left:none;padding:.05in 5.75pt .05in 5.75pt;height:12.2pt'>
  <p class=MsoNormal>The Patient Header has Patient Photo, Patient Id, Name,
  Age, Gender and Physician Name.</p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Case Title</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.2pt'>
  <p class=MsoNormal>The field is gives identification for a case can be
  retrieved easily at later date.</p>
  </td>
 </tr>
 <tr style='height:12.95pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal><b>Case Status</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal>This displays whether case is open or closed. Case can be
  edited only when it is open. The facility to close the case is with the
  physician.</p>
  </td>
 </tr>
 <tr style='height:12.95pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal><b>Complaint and symptoms</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal>The patient complaints and symptoms to be provided here.</p>
  </td>
 </tr>
 <tr style='height:12.95pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal><b>Attachments</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal>Any scanned image files of ECG, Xray, CT etc can be put in
  EMR through this field.</p>
  </td>
 </tr>
 <tr style='height:12.95pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal><b>Writing Pad</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal>There is support for a digital pen. You can buy our
  digital pen and use it to input the EMR.</p>
  </td>
 </tr>
 <tr style='height:12.95pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal><b>History</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal>This has 6 fields namely past illness, Family history,
  allergies, Current medication, immunization and any attachments of scanned
  old records.</p>
  </td>
 </tr>
 <tr style='height:12.95pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal><b>Vital Signs</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:12.95pt'>
  <p class=MsoNormal>This has Weight, Height, BP, temperature, Pulse Rate and
  Respiratory Rate, </p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>Order lab tests</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>Physician can order lab tests. Here a list of labs and
  their tests with whom the physician has enrolled will be shown.</p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>Diagnosis</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>Diagnosis, prescription, Impression.</p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>Second opinion</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>Physician can send the record to a list of Specialists he
  has enrolled and get second opinion.</p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>Save Button</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>When save button is clicked the data is saved in the
  server.</p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>Close Case</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>When close case button is clicked the case will be closed
  and will become non editable and inactive for labs and specialists.</p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>Exit EMR</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>This link is on the top and should be used to exit from
  the patient EMR and enter into patient list.</p>
  </td>
 </tr>
 <tr style='height:13.7pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal><b>To go to previous case</b></p>
  </td>
  <td width=474 valign=top style='width:355.5pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:.05in 5.75pt .05in 5.75pt;height:13.7pt'>
  <p class=MsoNormal>Physician can go to previous case through the numbers on
  top right or through the case title.</p>
  </td>
 </tr>
</table>

</div>

<p class=MsoNormal>&nbsp;</p>

<span style='font-size:11.0pt;font-family:"Times New Roman"'><br clear=all
style='page-break-before:always'>
</span>

<p class=MsoNormal><img width=627 height=851
src="images/helpimages/image019.gif"></p>

<h2><a name="chapter42">4.2 Laboratory Account</a></h2>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td width="7%">&nbsp;</td>
    <td><span class="MsoNormal"><a href="#chapter421">4.2.1 Patient List</a><br />
      <a href="#chapter422">4.2.2 Working on tests and reports</a><br />
      <a href="#chapter423">4.2.3 Lab services</a><br />
      <a href="#chapter424">4.2.4 My Network</a></span></td>
  </tr>
</table>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'><br />
  These are
  laboratories where clinical tests of patients are conducted. They have an
  account in this network to communicate with Physicians. An authorized person
  from the lab is designated to operate the account. Upon successful login the lab
  user is taken to Patient List page.</p>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter421">4.2.1 Patient
List</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>This is the list
of incoming patients from various physicians in the network. Click on doctor
name in the same row as the patient to know details of sender. To view the
tests ordered for the patient, click on Show services. User is taken to a
different page to work on tests and reports of the patient.</p>

<p class=MsoNormal align=center style='text-align:center'>&nbsp;</p>

<p class=MsoNormal align=center style='text-align:center'><img border=0
width=434 height=326 src="images/helpimages/image020.jpg"></p>

<h3 style='margin-left:.5in;text-indent:-.5in'><a name="chapter422">4.2.2 Working
on tests and reports</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>The list of
tests ordered is displayed here. The lab should conduct the test on the patient
on his arrival. On successful completion of the test, the lab user uploads the
test reports for the corresponding tests. </p>

<p class=MsoNormal align=center style='text-align:center'><img width=642
height=866 src="images/helpimages/image021.gif"></p>

<b><span style='font-size:12.0pt;font-family:Arial'><br clear=all
style='page-break-before:always'>
</span></b>

<h3 style='margin-left:.5in;text-indent:-.5in'><a name="chapter423">4.2.3 Lab
services</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Lab can manage
the clinical tests they provide in this section. The tests added in this
section are displayed to all the physicians so that they can order the required
tests.</p>

<p class=MsoNormal><img width=625 height=776
src="images/helpimages/image022.gif"></p>

<h3 style='margin-left:.5in;text-indent:-.5in'><a name="chapter424">4.2.4 My
Network</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>In this section
Lab user can build the network of Physicians and specialists who will send lab
orders to them. When a physician requests to network with the lab it is
displayed in the pending list. After confirmation the confirmed list of doctors
get updated.</p>

<p class=MsoNormal>&nbsp;</p>

<p class=MsoNormal>&nbsp;</p>

<div align=center>

<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
 style='border-collapse:collapse;border:none'>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>All (link)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border:solid windowtext 1.0pt;
  border-left:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>List of confirmed and pending physicians </p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Pending (link)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>List of all physicians who confirmation is pending</p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Confirm (button)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>All selected physicians are confirmed</p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Reject (button)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>All selected physicians are rejected</p>
  </td>
 </tr>
</table>

</div>

<p class=MsoNormal align=center style='text-align:center'>&nbsp;</p>

<p class=MsoNormal align=center style='text-align:center'><img border=0
width=434 height=326 src="images/helpimages/image023.jpg"></p>

<p class=MsoNormal align=center style='text-align:center'>&nbsp;</p>

<h2 style='margin-left:0in;text-indent:0in'><a name="chapter43">4.3 Specialist
Role</a></h2>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td width="7%">&nbsp;</td>
    <td><a href="#chapter431">4.3.1 Patient List</a><br />      
      <a href="#chapter432">4.3.2 EMR view of the specialist</a><br />      <a href="#chapter433">4.3.3 My Network</a><br />      <a href="#chapter434">4.3.4 Order Lab services</a><br />      
      <a href="#chapter435">4.3.5 Patient creation and referral</a></td>
  </tr>
</table>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'><br />
  All specialist
  doctors who want to provide opinion services to physicians can register in the
  network.</p>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter431">4.3.1 Patient
List</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Upon successful
login, user is shown the list of all incoming patients’ records referred by
physicians. Click EMR link to view the EMR of a patient.</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<p class=MsoNormal align=center style='text-align:center'><img border=0
width=386 height=288 src="images/helpimages/image024.jpg"></p>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter432">4.3.2   EMR
view of the specialist</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>All data saved
by the physician in patient EMR is in Read only mode. Upon review of the EMR,
specialist can provide opinions in “second opinion” section.</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'><img width=585
height=482 src="images/helpimages/image025.gif"></p>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter433">4.3.3   My
Network</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Specialist has
to create a network of physicians in order to receive the patient records.
Physicians send requests and specialist has to approve.  The list of links in
the page and the functionality is shown below.</p>

<p class=MsoNormal>&nbsp;</p>

<div align=center>

<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
 style='border-collapse:collapse;border:none'>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>All (link)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border:solid windowtext 1.0pt;
  border-left:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>List of confirmed and pending physicians </p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Pending (link)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>List of all physicians who confirmation is pending</p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Confirm (button)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>All selected physicians are confirmed</p>
  </td>
 </tr>
 <tr style='height:12.2pt'>
  <td width=127 valign=top style='width:95.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal><b>Reject (button)</b></p>
  </td>
  <td width=362 valign=top style='width:271.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:5.05pt 5.75pt 5.05pt 5.75pt;height:12.2pt'>
  <p class=MsoNormal>All selected physicians are rejected</p>
  </td>
 </tr>
</table>

</div>

<p class=MsoNormal style='margin-left:.5in'>&nbsp;</p>

<p class=MsoNormal style='margin-left:.5in'><img width=578 height=576
src="images/helpimages/image026.gif"></p>

<b><span style='font-size:12.0pt;font-family:Arial'><br clear=all
style='page-break-before:always'>
</span></b>

<h3 style='margin-left:0in;text-indent:0in'><a name="chapter434">4.3.4   Order
Lab services</a></h3>

<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Specialist has
an additional facility to order lab test for a patient on behalf of physician.
To order lab test, click on order test in the EMR. The labs of the physician
network and their tests are listed.  Choose the tests and order the same.</p>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'>&nbsp;</p>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td width="7%">&nbsp;</td>
    <td><a href="#chapter431">4.3.1 Patient List</a><br />
      <a href="#chapter432">4.3.2 EMR view of the specialist</a><br />
      <a href="#chapter433">4.3.3 My Network</a><br />
      <a href="#chapter434">4.3.4 Order Lab services</a><br />
      <a href="#chapter435">4.3.5 Patient creation and referral</a></td>
  </tr>
</table>
<p><a name="chapter435">4.3.5   Patient
  creation and referral</a> </p>
<p class=MsoNormal style='margin-left:.5in;text-align:justify'>Some specialists
  may want to create patients and take second opinion from other specialists.
  This can be achieved by subscribing to both referral and referring services. Specialists
  of this type are given screens of patient creation; refer a patient to another
  specialist along with receiving incoming patients. Patient creation and
  referral are explained in the earlier part of this document.</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p></p>
</div>

</body>

</html>
