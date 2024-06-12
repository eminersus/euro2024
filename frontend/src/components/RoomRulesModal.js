import React, { useState } from 'react';
import Modal from 'react-modal';

const RoomRulesModal = ({ isOpen, onClose, onSave }) => {
    const [maxUserNum, setMaxUserNum] = useState(128);
    const [expirationDate, setExpirationDate] = useState('');
    const [scoreWinnerPoint, setScoreWinnerPoint] = useState(0);
    const [sideWinnerPoint, setSideWinnerPoint] = useState(0);
    const [scoreWinnerOpen, setScoreWinnerOpen] = useState(false);

    const handleSave = () => {
        onSave({
            maxUserNum,
            expirationDate,
            scoreWinnerPoint,
            sideWinnerPoint,
            scoreWinnerOpen
        });
        onClose();
    };

    return (
        <Modal isOpen={isOpen} onRequestClose={onClose} contentLabel="Room Rules">
            <h2>Set Room Rules</h2>
            <form>
                <div>
                    <label>Max User Number:</label>
                    <input
                        type="number"
                        value={maxUserNum}
                        onChange={(e) => setMaxUserNum(e.target.value)}
                    />
                </div>
                <div>
                    <label>Expiration Date:</label>
                    <input
                        type="datetime-local"
                        value={expirationDate}
                        onChange={(e) => setExpirationDate(e.target.value)}
                    />
                </div>
                <div>
                    <label>Score Winner Point:</label>
                    <input
                        type="number"
                        value={scoreWinnerPoint}
                        onChange={(e) => setScoreWinnerPoint(e.target.value)}
                    />
                </div>
                <div>
                    <label>Side Winner Point:</label>
                    <input
                        type="number"
                        value={sideWinnerPoint}
                        onChange={(e) => setSideWinnerPoint(e.target.value)}
                    />
                </div>
                <div>
                    <label>Score Winner Open:</label>
                    <input
                        type="checkbox"
                        checked={scoreWinnerOpen}
                        onChange={(e) => setScoreWinnerOpen(e.target.checked)}
                    />
                </div>
                <button type="button" onClick={handleSave}>Save Rules</button>
                <button type="button" onClick={onClose}>Close</button>
            </form>
        </Modal>
    );
};

export default RoomRulesModal;
