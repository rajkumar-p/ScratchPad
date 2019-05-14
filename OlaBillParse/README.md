# OLA Bill Parse

## Overview
This utility parses a OLA bill and populates a Excel sheet.

## Pre-requisites
* Java8
* Maven 3+

## Build
You need to use maven to build this util. Run `mvn package` to generate the jar with dependencies. 

## Run
You need to first set the following environment variables. PARTNER_USER_ID, PARTNER_USER_SECRET, POLICY_ID and EMPLOYEE_EMAIL needs to be set. Then run using the command `java -jar OlaBillParse-1.0-SNAPSHOT-jar-with-dependencies.jar --invoicesDir="INVOICES DIRECTORY" --invoiceTemplate="TEMPLATE FILE LOCATION" --invoiceMonth="MONTH YEAR"`

## License
Free as in free speech.

## Contributions & Questions
Send me a mail on <raj@diskodev.com> or tweet me at <https://twitter.com/rajkumar_p>
