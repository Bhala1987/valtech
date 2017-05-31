package com.valtech.utility;

import com.valtech.pages.BaseActions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table extends BaseActions {

    /*****v******************************************************************
     ***********************   Xpaths for Table    ***************************
     *************************************************************************/

    private static String tableHeader(String tableName) {

        return String.valueOf("//h4[contains(text(),'" + tableName + "')]");
    }

    private static final By LIST_OF_TABLES = By.xpath("//h4");

    private static By tableArea(String tableName) {

        return By.xpath(tableHeader(tableName) + "/../../../../a");
    }

    private static By paginationArrow(String tableName, String leftRight) {

        if (leftRight.equals("left")) {
            leftRight = "1";
        }
        else {
            leftRight = "2";
        }
        return By.xpath(
                tableHeader(tableName) + "/../../../../../../..//div[@class='search-pagination']/a["
                        + leftRight
                        + "]");
    }

    private static By numberOfPagesInTable(String tableName) {

        return By.xpath(tableHeader(tableName)
                + "/../../../../../../..//div[@class='search-pagination']/div[@class='page']");
    }

    private static final By firstCell(String tableName) {

        return By.xpath(tableHeader(tableName) + "/../../../../../../..//tbody/tr[1]/td[1]");
    }

    private static By tableColumnsNames(String tableName) {

        return By.xpath(tableHeader(tableName) + "/../../../../../../..//table/thead//tr/th");
    }

    private static By numberOfRowsInSinglePage(String tableName) {

        return By.xpath(tableHeader(tableName) + "/../../../../../../..//tbody/tr");
    }

    public static By collapseButton(String tableName) {

        return By.xpath(tableHeader(tableName) + "/../../div[@class='panel-caret']");
    }

    public static By tabelCellByValue(String table, String rowNumber, String cellValue) {

        return By.xpath(tableHeader(table) + "/../../../../../../..//tbody/tr[" + rowNumber
                + "]/td[contains(text(), '"
                + cellValue + "')]");
    }

    public List<String> getListOfTables() throws Exception {

        return get.elementsText(LIST_OF_TABLES);
    }

    public By tableCellByColumnIndex(String tableName, String rowNumber, String columnNumber) {

        return By.xpath(
                tableHeader(tableName) + "/../../../../../../..//tbody/tr[" + rowNumber + "]/td["
                        + columnNumber
                        + "]");
    }

    public By buttonForTableCell(String tableName, String rowNumber, String columnName) {

        return By.xpath(
                tableHeader(tableName) + "/../../../../../../..//tbody/tr[" + rowNumber + "]/td["
                        + getColumnIndex(tableName, columnName)
                        + "]");
    }
    public By rowForTableCell(String tableName, int rowNumber) {

        return By.xpath(
                tableHeader(tableName) + "/../../../../../../..//tbody/tr[" + rowNumber + "]");
    }



    /*****v******************************************************************
     ***********************  Table Util Methods  ***************************
     ************************************************************************/

    private int getCurrentPageOfTable(String table) {

        return Integer.valueOf(getPagesInFractionFormat(table).get(0));
    }

    public void clickButtonForTableCell(String tableName, String rowNumber, String columnName) {
        action.clickElement(buttonForTableCell(tableName, rowNumber, columnName));
    }
    public String getTextForTableCell(String tableName, String rowNumber, String columnName) {
        return get.elementText(buttonForTableCell(tableName, rowNumber, columnName));
    }
    public void clickButtonOnFirstRowTableCell(String tableName, int rowNumber) {
         action.clickElement(rowForTableCell(tableName, rowNumber));
    }
    private List<String> getPagesInFractionFormat(String table) {

        expandTableIfHidden(table);
        if (is.elementDisplayed(paginationArrow(table, "left"))) {
            return Arrays.asList(get.elementText(numberOfPagesInTable(table)).split("/"));
        }
        else {
            return Arrays.asList("1", "1");
        }
    }

    private boolean isTableOnFirstPage(String tableName) {

        return getCurrentPageOfTable(tableName) == 1;
    }

    private void comeBackToFirstPage(String tableName) {

        for (int r = getCurrentPageOfTable(tableName); r >= 2; r--) {
            goToPreviousPage(tableName);
            waitForNextPageToBeDisplayed(tableName, r - 1);
        }
    }

    private void waitForTableDisplayed(String table) {

        for (int i = 0; i < 500; i++)

        {
            if (!isTableAreaHidden(table)) {
                break;
            }
            else {
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void goToPreviousPage(String tableName) {

        action.scrollToTopOfPage();
        action.clickElement(paginationArrow(tableName, "left"));
    }

    public int getNumberOfRows(String tableName) {

        return get.elementCount(numberOfRowsInSinglePage(tableName));
    }

    public int getColumnIndex(String tableName, String columnName) {

        return getListOfColumnNames(tableName).indexOf(columnName) + 1;
    }

    public List<String> getListOfAllRowsForColumn(String tableName, String columnName) {

        expandTableIfHidden(tableName);
        if (!isTableOnFirstPage(tableName)) {
            comeBackToFirstPage(tableName);
        }
        int totalNumberOfPages = getTotalNumberOfPages(tableName);
        int totalNumberOfRows = 0;
        String tableCellValue;
        List<String> listData = new ArrayList<>();
        for (int i = 1; i <= totalNumberOfPages; i++) {
            totalNumberOfRows = totalNumberOfRows + getNumberOfRows(tableName);
            for (int j = 1; j <= totalNumberOfRows; j++) {
                if (get.elementsText(firstCell(tableName)).contains("No data available in table")) {
                    tableCellValue = null;

                }
                else {
                    tableCellValue = get.elementText(
                            tableCellByColumnIndex(tableName, String.valueOf(j),
                                    String.valueOf(getColumnIndex(tableName, columnName))));
                }

                listData.add(tableCellValue);
            }
            if (i < totalNumberOfPages) {
                goToNextPage(tableName);
                waitForNextPageToBeDisplayed(tableName, i + 1);
            }
            totalNumberOfRows = 0;
        }
        return listData;
    }

    public List<String> getListOfColumnNames(String tableName) {

        expandTableIfHidden(tableName);
        waitFor.elementVisible(tableColumnsNames(tableName));
        return get.elementsText(tableColumnsNames(tableName));
    }

    public int getTotalNumberOfPages(String table) {

        return Integer.valueOf(getPagesInFractionFormat(table).get(1));
    }

    public void goToNextPage(String table) {

        action.scrollToTopOfPage();
        action.clickElement(paginationArrow(table, "right"));
    }

    public boolean isTableAreaHidden(String table) {

        return get.elementAttribute(tableArea(table), "aria-expanded").equals("false");
    }

    public boolean isPaginationWorksForMoreThanOnePage(String table) {

        expandTableIfHidden(table);
        List<String> pages = Arrays
                .asList(get.elementText(Table.numberOfPagesInTable(table)).split("/"));
        if (Integer.valueOf(pages.get(1)) > 1) {
            action.scrollElementIntoView(collapseButton(table));
            action.clickElement(Table.paginationArrow(table, "right"));
            waitForNextPageToBeDisplayed(table, 2);
            return (getCurrentPageOfTable(table) == 2);
        }
        return false;
    }

    public void clickOnCollapseButton(String table) {

        action.clickElement(collapseButton(table));
        waitForTableDisplayed(table);
    }

    public void waitForNextPageToBeDisplayed(String table, int expectedPageNumber)

    {

        for (int i = 0; i < 500; i++)

        {
            if (getCurrentPageOfTable(table) == expectedPageNumber) {
                break;
            }
            else {
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void expandTableIfHidden(String tableName) {

        if (isTableAreaHidden(tableName)) {
            clickOnCollapseButton(tableName);
        }
    }

    public void findTheRowAndClickOnIt(String tableName, String firstName, String lastName, String columnName) {

        expandTableIfHidden(tableName);
        int numberOfRows = getNumberOfRows(tableName);
        int totalPages = getTotalNumberOfPages(tableName);
        searchAndClick:
        for (int i = 0; i < totalPages; i++) {
            for (int j = 0; j <= numberOfRows; j++) {
                if (isTableRowFoundByCustomerName(firstName, lastName, tableName, String.valueOf(j+1))) {
                    clickButtonForTableCell(tableName, String.valueOf(j + 1), columnName);
                    break searchAndClick;
                }
            }
            goToNextPage(tableName);
            waitForNextPageToBeDisplayed(tableName, i + 1);
        }
    }
    public String findTheRowAndGetTextOnIt(String tableName, String firstName, String lastName, String columnName) {

        expandTableIfHidden(tableName);
        int numberOfRows = getNumberOfRows(tableName);
        int totalPages = getTotalNumberOfPages(tableName);

        for (int i = 0; i < totalPages; i++) {
            for (int j = 0; j <= numberOfRows; j++) {
                if (isTableRowFoundByCustomerName(firstName, lastName, tableName, String.valueOf(j+1))) {
                    return getTextForTableCell(tableName, String.valueOf(j + 1), columnName);
                }
            }
            goToNextPage(tableName);
            waitForNextPageToBeDisplayed(tableName, i + 1);
        }
        return null;
    }


    public int totalRowsInTable(String tableName) throws Exception {

        String tableHeader = get.elementText(By.cssSelector(tableHeader(tableName)));
        return Integer.parseInt(tableHeader.replaceAll("\\D+", ""));
    }

    public boolean isTableRowFoundByCustomerName(String firstName, String lastName, String tableName, String rowNumber) {
        return is.elementDisplayed(Table.tabelCellByValue(tableName, rowNumber, firstName)) && is.elementDisplayed(Table.tabelCellByValue(tableName, rowNumber, lastName));
    }

}
