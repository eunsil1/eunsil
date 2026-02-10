function changeTab(tabId){
  const allTabs = document.querySelectorAll('.tab');
  const allContent = document.querySelectorAll('.tab-content');

  allTabs.forEach(function(a){
    a.classList.remove('active-tab'); //active-tab 제거
  });

  allContent.forEach(function(a){
    a.classList.remove('active-content'); 
    //active-content 제거
  });

  const selectedTab = document.getElementById("menu" + tabId);
  selectedTab.classList.add('active-tab');

  const selectedContent = document.getElementById(tabId);
  selectedContent.classList.add('active-content');
}