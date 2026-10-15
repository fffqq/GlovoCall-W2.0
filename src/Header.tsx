import glovoLogo from "./assets/GlovoCallLogo.png";
import {useState} from "react";
import LoginElement from "./LoginElement.tsx";


export default function Header() {
    const [modal, setModal] = useState(false);
    return (

    <header className="flex flex-row min-h-50px justify-between min-w-screen">
        <img src={glovoLogo} alt="Glovo Call Logo" className="h-1/5 w-1/10"/>

        <button className="pr-5" onClick={()=>setModal(true)}>login</button>

        <LoginElement isOpen={modal} onClose={() => setModal(false)} />

    </header>
    );
}