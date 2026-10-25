import glovoLogo from "./assets/GlovoCallLogo.png";
import {useState} from "react";
import SignInSignOut from "./SignInSignOut.tsx";


export default function Header() {

    const [modal, setModal] = useState(false);
    const [authMod, setAuthMod] = useState<'signUp' | 'signIn'>('signUp');
    const handleOpen = (mode: 'signUp' | 'signIn') => {
        setAuthMod(mode);
        setModal(true);
    };
    return (

    <header className="flex flex-row min-h-50px justify-between min-w-screen">
        <img src={glovoLogo} alt="Glovo Call Logo" className="h-1/5 w-1/10"/>

        <button className="pr-5" onClick={()=>handleOpen('signUp')}>Sign up</button>
        <button className="pr-5" onClick={()=>handleOpen('signIn')}>Sign in</button>

        <SignInSignOut isOpen={modal} mode={authMod} onClose={() => setModal(false)} />

    </header>
    );
}