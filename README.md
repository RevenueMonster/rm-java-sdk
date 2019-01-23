[![Maven Central](https://img.shields.io/maven-central/v/com.github.revenuemonster/RevenueMonsterOpenAPI.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.revenuemonster%22%20AND%20a:%22RevenueMonsterOpenAPI%22)

# API-SDK-Java

This is an Java SDK that maps some of the RESTful methods of Open API that are documented at [doc.revenuemonster.my](https://doc.revenuemonster.my/).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

The external library u need to install in order to build this library (Maven):

- [com.fasterxml.jackson.core:jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.9.8)
- [commons-codec:commons-codec](https://mvnrepository.com/artifact/commons-codec/commons-codec/1.11)
- [commons-io:commons-io](https://mvnrepository.com/artifact/commons-io/commons-io/2.6)
- [org.bouncycastle:bcpkix-jdk15on](https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk15on/1.60)
- [org.bouncycastle:bcprov-jdk15on](https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on/1.60)
- [org.json:json](https://mvnrepository.com/artifact/org.json/json/20180813)

The Java framework you would need for this project is Java 8 or above

### Link your project with dependecy management

- Maven

```
<dependency>
  <groupId>com.github.revenuemonster</groupId>
  <artifactId>RevenueMonsterOpenAPI</artifactId>
  <version>1.0.1</version>
</dependency>
```

- Gradle

```
implementation 'com.github.revenuemonster:RevenueMonsterOpenAPI:1.0.1'
```

### Or simply download the jar file here and start using at your project

[Google Drive](https://drive.google.com/file/d/1SCuAHG_wABslfGy2HnzYcNgTL2vcQz75/view?usp=sharing)

### Covered Functions

- [x] Signature Algorithm
- [x] Client Credentials (Authentication)
- [x] Refresh Token (Authentication)
- [ ] Get Merchant Profile
- [ ] Get Merchant Subscriptions
- [ ] Get Stores
- [ ] Get Stores By ID
- [ ] Create Store
- [ ] Update Store
- [ ] Delete Store
- [ ] Get User Profile
- [x] Payment (Transaction QR) - Create Transaction QRCode/URL
- [x] Payment (Transaction QR) - Get Transaction QRCode/URL
- [x] Payment (Transaction QR) - Get Transaction QRCode/URL By Code
- [x] Payment (Transaction QR) - Get Transactions By Code
- [x] Payment (Quick Pay) - Payment
- [x] Payment (Quick Pay) - Refund
- [x] Payment (Quick Pay) - Reverse
- [ ] Payment (Quick Pay) - Get All Payment Transactions
- [x] Payment (Quick Pay) - Get All Payment Transaction By ID
- [x] Payment (Quick Pay) - Get All Payment Transaction By OID
- [ ] Payment (Quick Pay) - Daily Settlement Report
- [ ] Give Loyalty Point
- [ ] Get Loyalty Members
- [ ] Get Loyalty Member
- [ ] Get Loyalty Member Point History
- [ ] Issue Voucher
- [ ] Void Voucher
- [ ] Get Voucher By Code
- [ ] Get Voucher Batches
- [ ] Get Voucher Batch By Key
- [ ] Send Notification (Merchant)
- [ ] Send Notification (Store)
- [ ] Send Notification (User)

### Usage

1. "sandbox" is for sandbox environment.
2. "production" is for production environment.
3. Get Client ID and Client Secret from portal.
   ![ClientIDClientSecret](https://storage.googleapis.com/rm-portal-assets/img/rm-landing/clientIDclientSecret.png)
4. Generate private key and publci key from portal.
   ![PrivateKeyPublicKey](https://storage.googleapis.com/rm-portal-assets/img/rm-landing/privateKeypublicKey.PNG)
5. Store private key for own use and public key at portal.
   ![PastePublicKey](https://storage.googleapis.com/rm-portal-assets/img/rm-landing/pastePublicKey.png)
6. Set environment variables at begining of the project before using any of the library functions.

```
Environment environment = new Environment();
environment.setEnvironment(clientId, clientSecret, "sandbox");
```

- Sample to read private key file

```
String currentPath = System.getProperty("user.dir") + "/src/privateKey.pem";
String privateKey = getKey(currentPath);
environment.setPrivateKey(privateKey);

private static String getKey(String filename) throws IOException {
        //Read key from file
        String strKeyPEM = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line = br.readLine()) != null){
            strKeyPEM += line + "\n";
        }
        br.close();
        return strKeyPEM;
}
```

- Client Credentials (Authentication)
  - To get refresh token and access token(expired after 2 hours) with using provided clientId and clientSecret

```
Authentication authentication = new Authentication();
ClientCredentials clientCredentials = authentication.GetClientCredentials();
environment.setRefreshToken(clientCredentials.refreshToken);
environment.setAccessToken(clientCredentials.accessToken);
```

- Authorization Code (Authentication)
  - To get authorization code which a partner wants to request permission to develop an application of a merchant and exchange authorization code into access token and refresh token

```
Authentication authentication = new Authentication();
ClientCredentials clientCredentials = authentication.GetAuthorizationCode(authorizationCode,redirectUrl);
environment.setRefreshToken(clientCredentials.refreshToken);
environment.setAccessToken(clientCredentials.accessToken);
```

- Refresh Token (Authentication)
  - To get new access token(expired after 2 hours) with using provided clientId and clientSecret (recommended to schedule to run this fucntion on every less than 2 hours) in order to avoid expired access token error

```
Authentication authentication = new Authentication();
ClientCredentials clientCredentials = authentication.RefreshToken();
environment.setAccessToken(clientCredentials.accessToken);
```

- Create Transaction QRCode/URL (TransactionQR)
  - To create static/dynamic QR code for user scanning merchant's displayed QR

```
TreeMap<String,Object> data = new TreeMap<String,Object>();
data.put("amount",100);
data.put("currencyType","MYR");
String[] method = new String[1];
method[0] = "WECHATPAY";
data.put("method",method);
TreeMap<String,Object> expiry = new TreeMap<String,Object>();
expiry.put("type","PERMANENT");
data.put("expiry",expiry);
TreeMap<String,Object> order = new TreeMap<String,Object>();
order.put("details","1 x Coffee");
order.put("title","Sales");
data.put("order",order);
data.put("redirectUrl","https://www.google.com");
data.put("type","DYNAMIC");
data.put("storeId","123412341234");
data.put("isPreFillAmount",true);
Payment payment = new Payment();
TransactionQR result = payment.CreateTransactionQRCodeURL(data);
```

- Get Transaction QRCode/URL (TransactionQR)
  - To get all QR Code(s) generated previously in the system

```
Payment payment = new Payment();
TransactionQRs result = payment.GetPaymentTransactionQRCodeURL("10","PERMANENT","PERMANENT");
```

- Get Transaction QRCode/URL By Code (TransactionQR)
  - To get specific QR Code generated previously in the system, by passing in code in query parameter (/qrcode/...)

```
Payment payment = new Payment();
TransactionQR result = payment.GetPaymentTransactionQRCodeURLByCode(code);

```

- Get Transactions By Code (TransactionQR)
  - To get all transactions under existing QR code, by passing in code in query parameter (/qrcode/.../transactions)

```
 Payment payment = new Payment();
Transactions result = payment.GetTransactionsByCode(code);
```

- Payment (Quick Pay) - Payment
  - To make payment by scanning barcode/authcode from user

```
TreeMap<String,Object> data = new TreeMap<String,Object>();
data.put("authCode","161583080660761280"); //get from user payment barcode
data.put("ipAddress","127.0.0.1");
data.put("storeId","123412341234");
TreeMap<String,Object> order = new TreeMap<String,Object>();
order.put("amount",100);
order.put("currencyType","MYR");
order.put("id","182735122");
order.put("title","title");
order.put("description","desc");
order.put("additionalData","API Test");
data.put("order",order);
Payment payment = new Payment();
TransactionQuickPay result = payment.QuickPay(data);
```

- Payment (Quick Pay) - Refund
  - To refund the successful transactions

```
TreeMap<String,Object> data = new TreeMap<String,Object>();
data.put("transactionId","181203100634010427614646"); // get from user's transaction
data.put("reason","test");
TreeMap<String,Object> refund = new TreeMap<String,Object>();
refund.put("type","FULL");
refund.put("currencyType","MYR");
refund.put("amount",100);
data.put("refund",refund);
Payment payment = new Payment();
TransactionQuickPay result = payment.Refund(data);
```

- Payment (Quick Pay) - Reverse
  - To reverse time-out or problematic transaction

```
TreeMap<String,Object> data = new TreeMap<String,Object>();
data.put("orderId","12345678131");
Payment payment = new Payment();
TransactionQuickPay result = payment.Reverse(data);
```

- Payment (Quick Pay) - Get Payment Transaction By ID
  - To get details of a transaction by using transactionId

```
Payment payment = new Payment();
TransactionQuickPay result = payment.GetPaymentTransactionByID(transactionId);
```

- Payment (Quick Pay) - Get Payment Transaction By Order ID
  - To get details of a transaction by using orderId

```
Payment payment = new Payment();
TransactionQuickPay result = payment.GetPaymentTransactionByOrderID(orderId);
```
