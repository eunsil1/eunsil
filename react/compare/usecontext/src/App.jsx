import MainContent from "./components/MainContent";
import Header from "./components/Header";
import "./App.css";
import AppProvider from "./context/AppContext";

function App() {
  return (
    <AppProvider>
      <div className="app">
        <Header/>
        <MainContent/>
      </div>
    </AppProvider>
  )
}

export default App
//Context -> 전역데이터 통로
//Provider -> 데이터 공급자