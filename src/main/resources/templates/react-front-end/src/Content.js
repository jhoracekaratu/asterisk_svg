import RightNav from "./RightNav";
import LeftNav from "./LeftNav";
import Main from "./Main";
function Content(){
    return(
    <>
    <div className="Content" >
    <LeftNav/>
    <Main/>
    <RightNav/>
   

    </div>
   
    </>
    
    );
    }
    export default Content;