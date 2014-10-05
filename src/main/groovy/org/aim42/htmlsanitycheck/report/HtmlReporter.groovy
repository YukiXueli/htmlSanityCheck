// see end-of-file for license information
package org.aim42.htmlsanitycheck.report

import org.aim42.htmlsanitycheck.collect.SingleCheckResults
import org.aim42.htmlsanitycheck.collect.SinglePageResults

/**
 * write the findings report to HTML
 */
public class HtmlReporter extends Reporter {

    FileWriter writer

    HtmlReporter() {
        super()
    }


    @Override
    void initReport() {
        // TODO: creation of output html file is broken - FIXME

        String userDir = System.getProperty("user.dir");
        String CheckDirPath = userDir + "/build/";
        String CheckReportFileName = "index.html"
        writer = new FileWriter(CheckDirPath + CheckReportFileName)

        writer.write """
            <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
            <html><head><meta httpEquiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>HTML Sanity Check Results</title>
    <link href="css/base-style.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head><body><div id="content">"""
    }

    @Override
    void reportOverallSummary() {
        int percentageSuccessful = SummarizerUtil.percentSuccessful( totalNrOfChecks(), totalNrOfFindings())

        writer.write "<h1>Summary</h1>"

        println "checked ${totalNrOfChecks()} items on ${totalNrOfPages()} pages, "
        println "found   ${totalNrOfFindings()}, $percentageSuccessful% successful."
        println "-" * 50

    }

    @Override
    void reportPageFindings(SinglePageResults pageResult) {

    }

    // report the results for a single page
    private void reportSingleCheckSummary( SingleCheckResults checkResults ) {


    }



    void writeSummary(String what, int howMany) {
        writer.write """<td><div class="infoBox">
                       <div class="counter">$howMany</div>
                       <p>$what</p>
                   </div></td>"""
    }

    void writeSummaryPostfix(int percentage) {
        writer.write '''
 </tr>
                        </table>
                    </div>
                </td>
                <td>
                    <div class="infoBox success" id="successRate">
                        <div class="percent">$percentage%
                        </div>
                        <p>successful</p>
                    </div>
                </td>
            </tr>
        </table>
'''
    }

    private void writeSummaryPrefix() {
        writer.write
        '''
   <h1>Test Summary</h1>
    <div id="summary">
        <table>
            <tr>
                <td>
                    <div class="summaryGroup">
                        <table>
                            <tr>'''
    }



    @Override
    void closeReport() {
        writer.write """</div></body></html>"""
    }
}

/*======================================================================

Copyright 2014 Gernot Starke and aim42 contributors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an
 "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ======================================================================*/