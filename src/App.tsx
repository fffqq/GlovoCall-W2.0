import Header from './Header.tsx'
import Footer from "./Footer";
import Carousel from "./Carousel.tsx";

function App() {
    return (

        <div className="flex flex-col justify-between items-center font-mono bg-glovo-yellow min-h-screen">
            <Header></Header>
            <Carousel></Carousel>
            <Footer></Footer>
        </div>
    )
}

export default App