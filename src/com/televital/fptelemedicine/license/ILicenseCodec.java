 
package com.televital.fptelemedicine.license;

import com.televital.fptelemedicine.license.EncryptionException;

public interface ILicenseCodec
{
	//function declaration for encryption of the license string
	public String encrypt(String content) throws EncryptionException ;
	
	//function declaration for decryption of the license string
	public String decrypt(String key) throws EncryptionException ;
	
	//function declaration for parsing the license string
	/*
	 * @ returns null if the license string is tampered 
	 * @ returns License object if the license is valid
	 */
	//public License parse(String key) throws EncryptionException;
}
