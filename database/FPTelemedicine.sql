                                                                                                                                                                                                                                                                                                                                                            

-- ----------------------------
-- MySQL Database Script File
-- ----------------------------

--
-- Database Name : FPTelemedicine
--

DROP DATABASE /*!32312 IF EXISTS*/ FPTelemedicine;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ FPTelemedicine;

USE FPTelemedicine;


DROP TABLE IF EXISTS TBL_Login;
CREATE TABLE TBL_Login
(
  LoginId 			VARCHAR(255) NOT NULL PRIMARY KEY,
  Password			VARCHAR(255) NOT NULL,
  ActivationKey			VARCHAR(255) DEFAULT NULL UNIQUE KEY,
  CurrentLoginStatus	        ENUM('IN','OUT') DEFAULT NULL DEFAULT 'OUT',
  LastLoginTime			DATETIME DEFAULT NULL,
  AccountType			ENUM('ADMIN','DOCTOR','LAB') DEFAULT NULL,
  AccountStatus			ENUM('ENABLED','DISABLED','PENDING') NOT NULL DEFAULT 'ENABLED',
  TStamp			DATETIME NOT NULL,
  CSS				VARCHAR(255) DEFAULT NULL,
  Language			VARCHAR(255) DEFAULT NULL,
  MaxPageSize			INT UNSIGNED DEFAULT NULL,
  PERSON_TYPE			VARCHAR(255) DEFAULT NULL,
  PERSON_ID			VARCHAR(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Insert into TBL_Login values('admin','ptbH1jPPu1c=','aaaaaaaaaa','OUT',NULL,'ADMIN','ENABLED',SYSDATE(),'NULL','NULL','10','ADMIN','admin');

DROP TABLE IF EXISTS TBL_Admin;
CREATE TABLE TBL_Admin
(
  AdminId 				VARCHAR(255) NOT NULL PRIMARY KEY,
  FirstName 			VARCHAR(255) NOT NULL,
  LastName				VARCHAR(255) DEFAULT NULL,
  Address			VARCHAR(255) DEFAULT NULL,
  ContactNumber			VARCHAR(255) DEFAULT NULL,
  Email					VARCHAR(255) DEFAULT NULL,
  TStamp				DATETIME NOT NULL,
  FOREIGN KEY (AdminId) REFERENCES TBL_Login (LoginId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO TBL_Admin (AdminId,FirstName,LastName,Address,TStamp) values('admin','Global','Administrator','Bangalore India',SYSDATE());

DROP TABLE IF EXISTS TBL_Doctor;
CREATE TABLE TBL_Doctor
(
  DoctorId 		    VARCHAR(255) NOT NULL PRIMARY KEY,
  FirstName 		    VARCHAR(255) NOT NULL,
  LastName		    VARCHAR(255) DEFAULT NULL,
  Photo			    VARCHAR(255) DEFAULT NULL,
  Address		    VARCHAR(255) DEFAULT NULL,
  ContactNumber		    VARCHAR(255) DEFAULT NULL,
  Email			    VARCHAR(255) DEFAULT NULL,
  Education                 VARCHAR(255) DEFAULT NULL,
  Designation               VARCHAR(255) DEFAULT NULL,
  Identification            VARCHAR(255) DEFAULT NULL,
  ClinicName                VARCHAR(255) DEFAULT NULL,
  Specialty		    VARCHAR(255) DEFAULT NULL,
  Type			    ENUM('SPECALIST','FAMILYPHYSICIAN','BOTH'),
  TStamp		    DATETIME NOT NULL,
  Flag                VARCHAR(10) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_Lab;
CREATE TABLE TBL_Lab
(
  LabId 		  VARCHAR(255) NOT NULL PRIMARY KEY,
  LabName 		  VARCHAR(255) NOT NULL,
  Logo			  VARCHAR(255) DEFAULT NULL,
  Address		  VARCHAR(255) DEFAULT NULL,
  ContactNumber		  VARCHAR(255) DEFAULT NULL,
  Email			  VARCHAR(255) DEFAULT NULL,
  ContactPerson           VARCHAR(255) DEFAULT NULL,
  TStamp		  DATETIME NOT NULL,
  Flag                VARCHAR(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_Patient;
CREATE TABLE TBL_Patient
(
  PatientId 		   VARCHAR(255) NOT NULL PRIMARY KEY,
  Creator                  VARCHAR(255)  NULL ,
  FirstName 		   VARCHAR(255) NOT NULL,
  LastName		   VARCHAR(255) DEFAULT NULL,
  Gender		   VARCHAR(255) NOT NULL,
  DateOfBirth		   DATE NOT NULL,
  MaritalStatus		   VARCHAR(255) DEFAULT NULL,
  BloodGroup		   VARCHAR(255) DEFAULT NULL,
  Occupation		   VARCHAR(255) DEFAULT NULL,
  Photo			   VARCHAR(255) DEFAULT NULL,
  Address		   VARCHAR(255) DEFAULT NULL,
  ContactNumber		   VARCHAR(255) DEFAULT NULL,
  Email			   VARCHAR(255) DEFAULT NULL,
  TStamp		   DATETIME NOT NULL,
  INDEX(Creator),
   FOREIGN KEY (Creator) REFERENCES TBL_Doctor(DoctorId)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS TBL_History;
CREATE TABLE TBL_History
(
  HistoryId 		    VARCHAR(255) NOT NULL PRIMARY KEY,
  PatientId			    VARCHAR(255) NOT NULL,
  PastIllness 			VARCHAR(1048) DEFAULT NULL,
  FamilyHistory			VARCHAR(1048) DEFAULT NULL,
  CurrentMedication		VARCHAR(1048) DEFAULT NULL,
  Immunization			VARCHAR(1048) DEFAULT NULL,
  Allergies             VARCHAR(1048) DEFAULT NULL,
  Attachments           VARCHAR(255) DEFAULT NULL,
  INDEX(PatientId),
  FOREIGN KEY (PatientId) REFERENCES TBL_Patient(PatientId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS TBL_Case;
CREATE TABLE TBL_Case
(
  CaseId 		       VARCHAR(255) NOT NULL PRIMARY KEY,
  DoctorId              VARCHAR(255) NOT NULL, 
  PatientId		        VARCHAR(255) NOT NULL,
  CaseStatus			VARCHAR(255) NOT NULL,
  CaseTitle             VARCHAR(255) DEFAULT NULL,
  Complaint_Symptoms	       VARCHAR(2048) DEFAULT NULL,
  CaseDescription	       VARCHAR(2048) DEFAULT NULL,
  Attachments		       VARCHAR(255) DEFAULT NULL,
  Weight		       VARCHAR(255) DEFAULT NULL,
  Height		       VARCHAR(255) DEFAULT NULL,
  Temperature		       VARCHAR(255) DEFAULT NULL,
  BloodPressure		       VARCHAR(255) DEFAULT NULL,
  PulseRate		       VARCHAR(255) DEFAULT NULL,
  RespirationRate	       VARCHAR(255) DEFAULT NULL,
  LMP			       VARCHAR(255) DEFAULT NULL,
  Diagnosis                    VARCHAR(2048) DEFAULT NULL, 
  Prescription                 VARCHAR(2048) DEFAULT NULL,
  Impression                   VARCHAR(2048) DEFAULT NULL,
  StartTStamp		       DATETIME NOT NULL,
  EndTStamp		       DATETIME NOT NULL,
  INDEX(PatientId),
  INDEX(DoctorId),
  FOREIGN KEY (PatientId) REFERENCES TBL_Patient (PatientId),
  FOREIGN KEY (DoctorId) REFERENCES TBL_Doctor (DoctorId)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_LabServices;
CREATE TABLE TBL_LabServices
(
  LabServiceId 		 VARCHAR(255) NOT NULL PRIMARY KEY,
  ServiceName 		 VARCHAR(255) NOT NULL,
  LabId            VARCHAR(255) NOT NULL, 
  INDEX(LabId),
  FOREIGN KEY (LabId) REFERENCES TBL_Lab(LabId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS TBL_LabTest;
CREATE TABLE TBL_LabTest
(
  LabTestId 	          VARCHAR(255) NOT NULL PRIMARY KEY,
  LabServiceId                   VARCHAR(255) NOT NULL,
  CaseId 		  VARCHAR(255) NOT NULL,
  TestReport              VARCHAR(255) DEFAULT NULL,
  OrderedTime		 DATETIME NOT NULL,
  Flag                VARCHAR(10) NOT NULL,
  INDEX(LabServiceId),
  INDEX(CaseId),
  
  FOREIGN KEY (LabServiceId) REFERENCES TBL_LabServices(LabServiceId) ,
  FOREIGN KEY (CaseId)REFERENCES TBL_Case(CaseId) 
 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS TBL_Referral;
CREATE TABLE TBL_Referral
(
  ReferralId 		 VARCHAR(255) NOT NULL PRIMARY KEY,
  CaseId 		 VARCHAR(255) NOT NULL,
  SpecalistId            VARCHAR(255) NOT NULL, 
  FamilyPhysicianId	 VARCHAR(255) DEFAULT NULL,
  ReferredTime		 DATETIME NOT NULL,
  Opinions               VARCHAR(255) DEFAULT NULL,
  Flag                VARCHAR(10) NOT NULL,
  INDEX(CaseId),
  INDEX(SpecalistId),
  INDEX(FamilyPhysicianId),
  FOREIGN KEY (CaseId) REFERENCES TBL_Case(CaseId),
  FOREIGN KEY (SpecalistId)REFERENCES TBL_Doctor(DoctorId),
  FOREIGN KEY (FamilyPhysicianId) REFERENCES TBL_Doctor(DoctorId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_LastRecord;
CREATE TABLE TBL_LastRecord
(
 Code                          VARCHAR(255) NOT NULL PRIMARY KEY,
 LastId                        VARCHAR(255) NOT NULL UNIQUE KEY
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_FavSpecalist;
CREATE TABLE TBL_FavSpecalist
(
 FavSpecalistId                INT UNSIGNED NOT NULL PRIMARY KEY,
 FamilyPhysicianId             VARCHAR(255) NOT NULL, 
 SpecalistId                   VARCHAR(255) NOT NULL,
 AccountStatus			       ENUM('PENDING','CONFIRMED','REJECTED','REREQUESTED') NOT NULL DEFAULT 'PENDING',
 Flag                          VARCHAR(10) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_FavLab;
CREATE TABLE TBL_FavLab
(
 FavLabId               INT UNSIGNED NOT NULL PRIMARY KEY,
 FamilyPhysicianId      VARCHAR(255) NOT NULL, 
 LabId                  VARCHAR(255) NOT NULL,
 AccountStatus			ENUM('PENDING','CONFIRMED','REJECTED','REREQUESTED') NOT NULL DEFAULT 'PENDING',
 Flag                   VARCHAR(10) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;







