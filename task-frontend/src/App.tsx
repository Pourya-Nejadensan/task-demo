import Header from "./layouts/Header.tsx";
import Footer from "./layouts/Footer.tsx";
import HomePage from "./pages/HomePage.tsx";
import './App.css';

function App() {
    return (
        <div>
            <Header />
            <main>
                <HomePage />
            </main>
            <Footer />
        </div>
    );
}

export default App;