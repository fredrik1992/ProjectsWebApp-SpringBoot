function  cleanJsonString (jsonString) {
    let cleanNavData = jsonString.replaceAll("&quot",'"');
    let cleanNavData2 = cleanNavData.replaceAll(";",'');
    let result =JSON.parse(cleanNavData2);
    return result;
}