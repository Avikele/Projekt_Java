import "./readingScreen.scss";
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import StopIcon from '@mui/icons-material/Stop';
import { useState, useEffect } from "react";

const ReadingScreen = () => {
    const [hours, setHours] = useState(0);
    const [minutes, setMinutes] = useState(0);
    const [seconds, setSeconds] = useState(0);
    const [isRunning, setIsRunning] = useState(false);
    let timer;

    // Toggle Timer Function
    const toggleTimer = () => {
        setIsRunning(!isRunning);
    };

    // Timer Logic
    useEffect(() => {
        if (isRunning) {
            timer = setInterval(() => {
                setSeconds(prev => {
                    if (prev === 59) {
                        setMinutes(min => (min === 59 ? 0 : min + 1));
                        setHours(hr => (minutes === 59 ? hr + 1 : hr));
                        return 0;
                    }
                    return prev + 1;
                });
            }, 1000);
        } else {
            clearInterval(timer);
        }

        // Cleanup interval on component unmount
        return () => clearInterval(timer);
    }, [isRunning, minutes]);

    return (
        <div className="timerCard">
            <div className="timerCard2">
                <h2> Reading Title</h2>
                <div id="time">
                    <span className="digit" id="hr">
                        {String(hours).padStart(2, '0')}
                    </span>
                    <span className="txt">:</span>
                    <span className="digit" id="min">
                        {String(minutes).padStart(2, '0')}
                    </span>
                    <span className="txt">:</span>
                    <span className="digit" id="sec">
                        {String(seconds).padStart(2, '0')}
                    </span>
                </div>

                <button className="btn" id="toggle" onClick={toggleTimer}>
                    {isRunning ? <StopIcon className="icon"/> : <PlayArrowIcon className="icon"/>}
                </button>
                <button className="btn"> End of session</button>
            </div>
        </div>
    );
};

export default ReadingScreen;
