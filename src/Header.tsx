import glovoLogo from "./assets/GlovoCallLogo.png";
import {useState} from "react";
import SignInSignOut from "./SignInSignOut.tsx";


export default function Header() {
    const [modal, setModal] = useState(false);
    return (

    <header className="flex flex-row min-h-50px justify-between min-w-screen">
        <img src={glovoLogo} alt="Glovo Call Logo" className="h-1/5 w-1/10"/>

        <button className="pr-5" onClick={()=>setModal(true)}>Sign up</button>

        <SignInSignOut isOpen={modal} onClose={() => setModal(false)} />

    </header>
    );
}