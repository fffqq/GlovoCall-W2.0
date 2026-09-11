import glovoLogo from "./assets/GlovoCallLogo.png";


export default function Header() {
    return (
    <header className="flex flex-row min-h-50px justify-between min-w-screen">
        <img src={glovoLogo} alt="Glovo Call Logo" className="h-1/5 w-1/10"/>

        <button className="pr-5">login</button>


    </header>
    );
}